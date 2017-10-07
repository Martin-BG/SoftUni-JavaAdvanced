package Lesson06FunctionalProgramming.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Pr05FilterByAge {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> people = new LinkedHashMap<>();

        int peopleToRead = Integer.parseInt(reader.readLine());
        while (peopleToRead-- > 0) {
            String[] tokens = reader.readLine().trim().split(",\\s+");
            people.put(tokens[0], Integer.parseInt(tokens[1]));
        }
        String condition = reader.readLine();
        int age = Integer.parseInt(reader.readLine());
        String format = reader.readLine();

        Predicate<Map.Entry<String, Integer>> ageTester = createAgeTester(condition, age);
        Consumer<Map.Entry<String, Integer>> printByFormat = createPrintByFormat(format);

        printFilteredPersons(people, ageTester, printByFormat);
    }

    private static void printFilteredPersons(Map<String, Integer> people,
                                             Predicate<Map.Entry<String, Integer>> ageTester,
                                             Consumer<Map.Entry<String, Integer>> printByFormat) {
        people.entrySet().stream().filter(ageTester).forEach(printByFormat);
    }

    private static Consumer<Map.Entry<String, Integer>> createPrintByFormat(String format) {
        switch (format) {
        case "name":
            return person -> System.out.printf("%s%n", person.getKey());
        case "age":
            return person -> System.out.printf("%d%n", person.getValue());
        case "name age":
        default:
            return person -> System.out.printf("%s - %d%n", person.getKey(), person.getValue());
        }
    }

    private static Predicate<Map.Entry<String, Integer>> createAgeTester(String condition, int age) {
        switch (condition) {
        case "younger":
            return person -> person.getValue() < age;
        case "older":
        default:
            return person -> person.getValue() >= age;
        }
    }
}