package Lesson06FunctionalProgramming.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

public class Pr07PredicateForNames {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int maxLength = Integer.parseInt(reader.readLine());

        BiPredicate<String, Integer> checkLength = (str, len) -> str.length() <= len;
        Consumer<String> print = System.out::println;

        Arrays.stream(reader.readLine().split("\\s+"))
                .filter(name -> checkLength.test(name, maxLength)) // .filter(str -> str.length() <= maxLength)
                .forEach(print);     // .forEach(System.out::println)
    }
}