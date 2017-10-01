package Lesson04DataRepresentationAndDataManipulation.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr07FindTheMissingNumberUsingXOR {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int endNumber = Integer.parseInt(reader.readLine());

        int[] numbers = Arrays.stream(reader.readLine().split(", "))
                .mapToInt(Integer::parseInt).toArray();

        // Algorithm: http://www.geeksforgeeks.org/find-the-missing-number/
        int missingNumber = 0;
        for (int number : numbers) {
            missingNumber ^= number;
        }

        for (int i = 1; i <= endNumber; i++) {
            missingNumber ^= i;
        }

        System.out.println(missingNumber);
    }
}