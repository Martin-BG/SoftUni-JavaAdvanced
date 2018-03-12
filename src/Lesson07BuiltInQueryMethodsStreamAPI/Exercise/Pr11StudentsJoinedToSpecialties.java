package Lesson07BuiltInQueryMethodsStreamAPI.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Pr11StudentsJoinedToSpecialties {

    private static Map<Long, List<String>> studentSpecialties;
    private static Set<Student> students;

    public static void main(String[] args) {

        studentSpecialties = new HashMap<>();

        students = new TreeSet<>(Comparator
                .comparing(Student::getName)
                .thenComparing(Student::getFacultyNumber));

        readInputData();

        students.forEach(student -> {
            List<String> specialties = studentSpecialties.get(student.getFacultyNumber());
            if (specialties != null) {
                specialties.forEach(specialty ->
                        System.out.printf("%s %d %s%n",
                                student.getName(),
                                student.getFacultyNumber(),
                                specialty));
            }
        });
    }

    private static void readInputData() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String line;

            while (!"Students:".equalsIgnoreCase(line = reader.readLine().trim())) {
                String[] tokens = line.split("\\s+");

                String specialty = tokens[0] + " " + tokens[1];
                long facultyNumber = Long.parseLong(tokens[2]);

                studentSpecialties.putIfAbsent(facultyNumber, new LinkedList<>());
                studentSpecialties.get(facultyNumber).add(specialty);
            }

            while (!"END".equalsIgnoreCase(line = reader.readLine().trim())) {
                String[] tokens = line.split("\\s+");

                long facultyNumber = Long.parseLong(tokens[0]);
                String name = tokens[1] + " " + tokens[2];

                students.add(new Student(name, facultyNumber));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Student {

        String name;
        long facultyNumber;

        Student(String name, long facultyNumber) {
            this.name = name;
            this.facultyNumber = facultyNumber;
        }

        String getName() {
            return this.name;
        }

        long getFacultyNumber() {
            return this.facultyNumber;
        }
    }
}