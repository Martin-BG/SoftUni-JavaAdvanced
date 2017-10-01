package Lesson04DataRepresentationAndDataManipulation.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr01ReverseArrayUsingRecursion {

    public static void main(String[] args) throws IOException {
        int[] arr = readNumbers();
        reverseArray(arr, 0, arr.length - 1);
        printNumbers(arr);
    }

    private static void reverseArray(int[] arr, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            swap(arr, startIndex, endIndex);
            reverseArray(arr, startIndex + 1, endIndex - 1);
        }
    }

    private static void swap(int[] arr, int startIndex, int endIndex) {
        int temp = arr[startIndex];
        arr[startIndex] = arr[endIndex];
        arr[endIndex] = temp;
    }

    private static int[] readNumbers() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
    }

    private static void printNumbers(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        for (int number : numbers) {
            sb.append(number).append(" ");
        }
        sb.delete(sb.length() - 1, sb.length());
        System.out.println(sb);
    }
}
