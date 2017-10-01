package Lesson04DataRepresentationAndDataManipulation.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr03BinarySearchUsingRecursion {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] sortedArray = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        int number = Integer.parseInt(reader.readLine());
        System.out.println(recursiveBinarySearch(sortedArray, number, 0, sortedArray.length - 1));
    }

    private static int recursiveBinarySearch(int[] sortedArray, int number, int lo, int hi) {
        if (lo > hi) {
            return -1;
        }

        int mid = lo + (hi - lo) / 2;

        if (number > sortedArray[mid]) {
            return recursiveBinarySearch(sortedArray, number, mid + 1, hi);
        } else if (number < sortedArray[mid]) {
            return recursiveBinarySearch(sortedArray, number, lo, mid - 1);
        } else {
            return mid;
        }
    }
}