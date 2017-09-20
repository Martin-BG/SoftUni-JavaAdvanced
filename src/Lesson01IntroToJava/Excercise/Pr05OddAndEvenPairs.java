package Lesson01IntroToJava.Excercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr05OddAndEvenPairs {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] nums = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        if (nums.length < 2 || nums.length % 2 != 0) {
            System.out.println("invalid length");
        } else {
            for (int i = 0; i < nums.length - 1; i += 2) {
                System.out.printf("%d, %d -> ", nums[i], nums[i + 1]);

                boolean firstIsEven = nums[i] % 2 == 0;
                boolean secondIsEven = nums[i + 1] % 2 == 0;

                if (firstIsEven && secondIsEven) {
                    System.out.printf("both are even%n");
                } else if (firstIsEven != secondIsEven) {
                    System.out.printf("different%n");
                } else {
                    System.out.printf("both are odd%n");
                }
            }
        }
    }
}
