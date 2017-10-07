package Lesson06FunctionalProgramming.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

public class Pr02SumNumbers {

    public static void main(String[] args) throws IOException {
        String[] input = new BufferedReader(new InputStreamReader(System.in)).readLine().split(",\\s+");

        Function<String, Integer> parseInt = Integer::parseInt;

        int sum = 0;
        for (String n : input) {
            sum += parseInt.apply(n);
        }

        System.out.printf("Count = %d%n", input.length);
        System.out.printf("Sum = %d%n", sum);
    }
}