package Lesson06FunctionalProgramming.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Function;

public class Pr08FindTheSmallestElement {

    public static void main(String[] args) throws IOException {
        Function<int[], Integer> getMinNumIndex = arr -> {
            int min = Integer.MAX_VALUE;
            int index = -1;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] <= min) {
                    min = arr[i];
                    index = i;
                }
            }
            return index;
        };

        int[] numbers = Arrays
                .stream(new BufferedReader(new InputStreamReader(System.in)).readLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.println(getMinNumIndex.apply(numbers));
    }
}