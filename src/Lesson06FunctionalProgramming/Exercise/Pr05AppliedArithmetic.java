package Lesson06FunctionalProgramming.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Function;

public class Pr05AppliedArithmetic {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = Arrays
                .stream(reader.readLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        boolean readNextCommand = true;
        while (readNextCommand) {
            String command = reader.readLine().trim().toLowerCase();

            switch (command) {
            case "end":
                readNextCommand = false;
                break;
            case "print":
                System.out.println(Arrays.toString(numbers).replaceAll("[,\\]\\[]", ""));
                break;
            default:
                Function<Integer, Integer> action = getAction(command);
                for (int i = 0; i < numbers.length; i++) {
                    numbers[i] = action.apply(numbers[i]);
                }
                break;
            }
        }
    }

    private static Function<Integer, Integer> getAction(String action) {
        switch (action) {
        case "add":
            return num -> num + 1;
        case "multiply":
            return num -> num * 2;
        case "subtract":
        default:
            return num -> num - 1;
        }
    }
}