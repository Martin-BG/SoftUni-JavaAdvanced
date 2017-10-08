package Lesson06FunctionalProgramming.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

public class Pr10ListOfPredicates {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int range = Integer.parseInt(reader.readLine());
        int[] divisors = Arrays
                .stream(reader.readLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        BiPredicate<Integer, int[]> isDivisible = (num, divs) -> {
            for (int div : divs) {
                if (num % div != 0) {
                    return false;
                }
            }
            return true;
        };

        IntStream.rangeClosed(1, range)
                .filter(x -> isDivisible.test(x, divisors))
                .forEach(x -> System.out.printf("%d ", x));
    }
}