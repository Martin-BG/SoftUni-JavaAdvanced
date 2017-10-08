package Lesson06FunctionalProgramming.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Function;

public class Pr03CustomMinFunction {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = Arrays
                .stream(reader.readLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        //Function<int[], Integer> smallestNum = x -> Arrays.stream(x).reduce(Integer.MAX_VALUE, Integer::min);

        Function<int[], Integer> smallestNum = numArr -> {
            int min = Integer.MAX_VALUE;
            for (int num : numArr) {
                if (min > num) {
                    min = num;
                }
            }
            return min;
        };

        System.out.println(smallestNum.apply(numbers));
    }
}