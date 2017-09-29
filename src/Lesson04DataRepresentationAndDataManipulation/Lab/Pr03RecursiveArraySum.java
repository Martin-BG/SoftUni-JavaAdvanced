package Lesson04DataRepresentationAndDataManipulation.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr03RecursiveArraySum {

    public static void main(String[] args) throws IOException {
        int[] numbers = readNumbers();
        System.out.println(getArraySum(numbers, 0));
    }

    private static long getArraySum(int[] numbers, int index) {
        if (index == numbers.length - 1) {
            return numbers[index];
        }
        return (numbers[index] + getArraySum(numbers, index + 1));
    }

    private static int[] readNumbers() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
    }
}
