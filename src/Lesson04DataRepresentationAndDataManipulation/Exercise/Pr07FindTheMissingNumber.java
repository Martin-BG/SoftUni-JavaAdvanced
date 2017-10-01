package Lesson04DataRepresentationAndDataManipulation.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr07FindTheMissingNumber {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int endNumber = Integer.parseInt(reader.readLine());
        int[] numbers = Arrays.stream(reader.readLine().split(", "))
                .mapToInt(Integer::parseInt).toArray();
        Arrays.sort(numbers);
        int expectedNumber = 1;
        for (int number : numbers) {
            if (number == expectedNumber) {
                expectedNumber++;
                continue;
            }
            break;
        }
        System.out.println(expectedNumber);
    }
}
