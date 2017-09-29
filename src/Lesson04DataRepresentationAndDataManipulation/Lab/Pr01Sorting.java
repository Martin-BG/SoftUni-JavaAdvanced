package Lesson04DataRepresentationAndDataManipulation.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr01Sorting {

    public static void main(String[] args) throws IOException {
        int[] numbers = readNumbers();
        if (numbers.length > 1) {
            if (numbers[0] > numbers[1]) { // Semi-random selection of algorithm
                bubbleSort(numbers);
            } else { // Selection sort could be faster on nearly sorted arrays
                selectionSort(numbers);
            }
        }
        printNumbers(numbers);
    }

    private static void selectionSort(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] < numbers[minIndex]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                swap(numbers, i, minIndex);
            }
        }
    }

    private static void bubbleSort(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            boolean isSorted = true;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] > numbers[j]) {
                    swap(numbers, i, j);
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    private static void printNumbers(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        for (int number : numbers) {
            sb.append(number).append(" ");
        }
        sb.delete(sb.length() - 1, sb.length());
        System.out.println(sb);
    }

    private static int[] readNumbers() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
    }
}
