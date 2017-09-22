package Lesson02Abstraction.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr03SumMatrixElements {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] dimensions = Arrays.stream(reader.readLine().split(", "))
                .mapToInt(Integer::parseInt).toArray();

        int rows = dimensions[0];
        int cols = dimensions[1];
        long sum = 0L;

        for (int i = 0; i < rows; i++) {
            int[] row = Arrays.stream(reader.readLine().split(", "))
                    .mapToInt(Integer::parseInt).toArray();

            for (int num : row) {
                sum += num;
            }
        }

        System.out.println(rows);
        System.out.println(cols);
        System.out.println(sum);
    }
}
