package Lesson06FunctionalProgramming.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Predicate;

public class Pr04FindEvensOrOdds {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] bounds = Arrays
                .stream(reader.readLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        String type = reader.readLine().trim().toLowerCase();

        printNumbers(bounds[0], bounds[1], getEvenOrOddPredicate(type));
    }

    private static void printNumbers(int lowBound, int highBound, Predicate<Integer> evenOrOddPredicate) {
        for (int num = lowBound; num <= highBound; num++) {
            if (evenOrOddPredicate.test(num)) {
                System.out.printf("%d ", num);
            }
        }
    }

    private static Predicate<Integer> getEvenOrOddPredicate(String type) {
        switch (type) {
        case "even":
            return num -> num % 2 == 0;
        case "odd":
        default:
            return num -> num % 2 != 0;
        }
    }
}