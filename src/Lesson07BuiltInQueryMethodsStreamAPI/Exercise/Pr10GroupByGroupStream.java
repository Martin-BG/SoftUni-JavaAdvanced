package Lesson07BuiltInQueryMethodsStreamAPI.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Pr10GroupByGroupStream {

    private static final String NAME = "name";
    private static final String GROUP = "group";
    private static final Pattern PERSON_PATTERN =
            Pattern.compile("^\\s*(?<" + NAME + ">.+)\\s+(?<" + GROUP + ">\\d+)\\s*$");

    public static void main(String[] args) {
        Function<Matcher, Person> matcherToPerson = matcher ->
                new Person(matcher.group(NAME), Integer.parseInt(matcher.group(GROUP)));

        Function<Map.Entry<Integer, List<Person>>, String> groupToString = groupPeople ->
                String.format("%d - %s",
                        groupPeople.getKey(),
                        groupPeople.getValue()
                                .stream()
                                .map(Person::getName)
                                .collect(Collectors.joining(", ")));

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8))) {

            String groups = reader
                    .lines()
                    .takeWhile(line -> !"END".equals(line))
                    .map(PERSON_PATTERN::matcher)
                    .filter(Matcher::matches)
                    .map(matcherToPerson)
                    .collect(Collectors.groupingBy(Person::getGroup))
                    .entrySet()
                    .stream()
                    .map(groupToString)
                    .collect(Collectors.joining(System.lineSeparator()));

            System.out.println(groups);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final class Person {

        private final String name;
        private final int group;

        private Person(String name, int group) {
            this.name = name;
            this.group = group;
        }

        private String getName() {
            return this.name;
        }

        private int getGroup() {
            return this.group;
        }
    }
}
