package Lesson01IntroToJava.Excercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr08FirstOddOrEvenElements {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] nums = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        String[] tokens = reader.readLine().split("\\s+");

        int mask = ("odd".equals(tokens[2].toLowerCase())) ? 1 : 0;

        int elements = Integer.parseInt(tokens[1]);

        for (int i = 0; i < nums.length && elements > 0; i++) {
            if (Math.abs(nums[i] % 2) == mask) {
                System.out.printf("%d ", nums[i]);
                elements--;
            }
        }
    }
}
