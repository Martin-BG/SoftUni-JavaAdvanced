package Lesson06FunctionalProgramming.Lab;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Pr05FilterByAgeWithPersonClass {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        IntFunction<String> consoleLineReader = ignored -> scanner.nextLine();

        List<Person> people = IntStream
                .range(0, Integer.parseInt(scanner.nextLine()))
                .mapToObj(consoleLineReader)
                .map(Person.STRING_TO_PERSON_FUNCTION)
                .collect(Collectors.toList());

        String condition = scanner.nextLine();
        int age = Integer.parseInt(scanner.nextLine());
        String format = scanner.nextLine();

        Function<Person, String> formatter = personFormatter(format);
        Predicate<Person> filter = ageComparator(condition, age);

        String result = people
                .stream()
                .filter(filter)
                .map(formatter)
                .collect(Collectors.joining(System.lineSeparator()));

        System.out.println(result);
    }

    private static Function<Person, String> personFormatter(String format) {
        switch (format) {
        case "name":
            return Person::getName;
        case "age":
            return person -> Integer.toString(person.getAge());
        case "name age":
            return person -> String.format("%s - %d", person.getName(), person.getAge());
        default:
            throw new IllegalArgumentException("Unknown format: " + format);
        }
    }

    private static Predicate<Person> ageComparator(String condition, int age) {
        switch (condition) {
        case "older":
            return person -> person.age >= age;
        case "younger":
            return person -> person.age <= age;
        default:
            throw new IllegalArgumentException("Unknown condition" + condition);
        }
    }

    private static final class Person {
        public static final Function<String, Person> STRING_TO_PERSON_FUNCTION = input -> {
            String[] tokens = Objects
                    .requireNonNull(input, "Null person input")
                    .split(", ");

            if (tokens.length != 2) {
                throw new IllegalArgumentException("Invalid person input: " + input);
            }

            String personName = tokens[0];
            int personAge = Integer.parseInt(tokens[1]);
            return new Person(personName, personAge);
        };

        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}