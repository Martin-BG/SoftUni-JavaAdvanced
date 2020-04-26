package RandomSolutions.JavaFundamentals.L06ObjectsAndClasses.MoreExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Pr01CompanyRoster {

    public static void main(String[] args) throws IOException {
        Company company = new Company();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        reader.lines()
                .limit(Long.parseLong(reader.readLine()))
                .map(EmployeeData::fromString)
                .forEach(company::addEmployee);

        Comparator<Department> departmentByHighestAverageSalaryCmp = Comparator
                .comparingDouble(Department::averageSalary)
                .reversed();

        Comparator<Employee> employeeByHighestSalaryCmp = Comparator
                .comparingDouble(Employee::getSalary)
                .reversed();

        String departmentInfo = company
                .firstDepartmentBy(departmentByHighestAverageSalaryCmp)
                .map(departmentInfo("Highest Average Salary: ", employeeByHighestSalaryCmp))
                .orElseThrow(() -> new IllegalStateException("Empty company"));

        System.out.println(departmentInfo);
    }

    private static Function<Department, String> departmentInfo(String header,
                                                               Comparator<? super Employee> employeeComparator) {
        return department -> header + department.getName() + System.lineSeparator() +
                department.employees().stream()
                        .sorted(employeeComparator)
                        .map(Pr01CompanyRoster::employeeToString)
                        .collect(Collectors.joining(System.lineSeparator()));
    }

    private static String employeeToString(Employee employee) {
        return String.format("%s %.2f %s %d",
                employee.getName(), employee.getSalary(),
                employee.getEmail(), employee.getAge());
    }

    private static final class EmployeeData {
        private static final String EMPLOYEE_REGEX =
                "^(?<name>\\S+)\\s+(?<salary>\\d+\\.*\\d+)\\s+(?<position>\\S+)\\s+" +
                        "(?<department>\\S+)\\s*(?<email>\\S+@\\S+\\.\\S+)?\\s*(?<age>\\d+)?$";

        private static final Pattern EMPLOYEE_PATTERN = Pattern.compile(EMPLOYEE_REGEX);

        private final String name;
        private final String email;
        private final int age;
        private final double salary;
        private final String position;
        private final String department;

        private EmployeeData(String name, String email, int age, double salary, String position, String department) {
            this.name = name;
            this.email = email;
            this.age = age;
            this.salary = salary;
            this.position = position;
            this.department = department;
        }

        public static EmployeeData fromString(String employeeString) {
            Matcher matcher = EMPLOYEE_PATTERN.matcher(employeeString);

            if (!matcher.matches()) {
                throw new IllegalArgumentException("Invalid employee string: " + employeeString);
            }

            String name = Objects.requireNonNull(matcher.group("name"),
                    () -> "Missing name in: " + employeeString);
            double salary = Double.parseDouble(Objects.requireNonNull(matcher.group("salary"),
                    () -> "Missing salary in: " + employeeString));
            String position = Objects.requireNonNull(matcher.group("position"),
                    () -> "Missing position in: " + employeeString);
            String department = Objects.requireNonNull(matcher.group("department"),
                    () -> "Missing department in: " + employeeString);
            String email = Objects.requireNonNullElse(matcher.group("email"), "n/a");
            int age = Integer.parseInt(Objects.requireNonNullElse(matcher.group("age"), "-1"));

            return new EmployeeData(name, email, age, salary, position, department);
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public int getAge() {
            return age;
        }

        public double getSalary() {
            return salary;
        }

        public String getPosition() {
            return position;
        }

        public String getDepartment() {
            return department;
        }
    }

    private static final class Company {
        private final Map<String, Department> departments;

        public Company() {
            departments = new HashMap<>();
        }

        public void addEmployee(EmployeeData employeeData) {
            String departmentName = employeeData.getDepartment();
            Department department = departments.compute(departmentName,
                    (name, dep) -> Objects.requireNonNullElseGet(dep, () -> new Department(departmentName)));
            Employee employee = new Employee(employeeData, department);
            department.addEmployee(employee);
        }

        public Optional<Department> firstDepartmentBy(Comparator<? super Department> comparator) {
            return departments.values().stream().min(comparator);
        }
    }

    private static final class Employee {
        private final String name;
        private final String email;
        private final int age;
        private final double salary;
        private final String position;
        private final Department department;

        public Employee(EmployeeData employeeData, Department department) {
            this.name = employeeData.getName();
            this.salary = employeeData.getSalary();
            this.email = employeeData.getEmail();
            this.age = employeeData.getAge();
            this.position = employeeData.getPosition();
            this.department = department;
        }

        public double getSalary() {
            return salary;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public int getAge() {
            return age;
        }
    }

    private static final class Department {
        private final String name;
        private final Collection<Employee> employees;

        public Department(String name) {
            this.name = name;
            employees = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public void addEmployee(Employee employee) {
            employees.add(employee);
        }

        public double averageSalary() {
            return employees.stream()
                    .mapToDouble(employee -> employee.salary)
                    .average()
                    .orElse(0.0);
        }

        public Collection<Employee> employees() {
            return Collections.unmodifiableCollection(employees);
        }
    }
}
