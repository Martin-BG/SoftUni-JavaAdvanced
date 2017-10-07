package Lesson06FunctionalProgramming.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

public class Pr03CountUppercaseWords {

    public static void main(String[] args) throws IOException {
        Predicate<String> startsWithUpperCase = x -> Character.isUpperCase(x.charAt(0));

        ArrayList<String> upperCaseWords = new ArrayList<>();
        Arrays.stream(new BufferedReader(new InputStreamReader(System.in)).readLine().split("\\s+"))
                .filter(startsWithUpperCase)
                .forEach(upperCaseWords::add);

        System.out.println(upperCaseWords.size());
        upperCaseWords.forEach(System.out::println);
    }
}