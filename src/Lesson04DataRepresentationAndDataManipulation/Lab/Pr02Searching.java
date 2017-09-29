package Lesson04DataRepresentationAndDataManipulation.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr02Searching {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        int number = Integer.parseInt(reader.readLine());

        int index;
        if (numbers.length < 10) {
            index = linearSearch(numbers, number);
        } else {
            index = binarySearch(numbers, number);
        }

        System.out.println(index);
    }

    private static int binarySearch(int[] numbers, int number) {
        int low = 0;
        int hi = numbers.length - 1;
        while (low <= hi) {
            int mid = low + (hi - low) / 2;
            if (numbers[mid] > number) {
                hi = mid - 1;
            } else if (numbers[mid] < number) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    private static int linearSearch(int[] numbers, int number) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == number) {
                return i;
            }
        }
        return -1;
    }
}
