package Lesson06FunctionalProgramming.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.BiPredicate;

public class Pr06ReverseAndExclude {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = Arrays
                .stream(reader.readLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int div = Integer.parseInt(reader.readLine());

        BiPredicate<Integer, Integer> exclude = (n, d) -> n % d == 0;

        for (int i = numbers.length - 1; i >= 0; i--) {
            if (!exclude.test(numbers[i], div)) {
                System.out.printf("%d ", numbers[i]);
            }
        }
    }
}