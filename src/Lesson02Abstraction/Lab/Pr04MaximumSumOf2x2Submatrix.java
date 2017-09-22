package Lesson02Abstraction.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr04MaximumSumOf2x2Submatrix {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] dimensions = Arrays.stream(reader.readLine().split(", "))
                .mapToInt(Integer::parseInt).toArray();

        int rows = dimensions[0];
        int cols = dimensions[1];

        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            int[] numbers = Arrays.stream(reader.readLine().split(", "))
                    .mapToInt(Integer::parseInt).toArray();

            System.arraycopy(numbers, 0, matrix[row], 0, cols);
        }

        long maxSum = Long.MIN_VALUE;
        int startRow = 0;
        int startCol = 0;

        for (int row = 0; row < rows - 1; row++) {
            for (int col = 0; col < cols - 1; col++) {
                long currentSum = calcSum(matrix, row, col);

                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    startRow = row;
                    startCol = col;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int row = startRow; row < startRow + 2; row++) {
            for (int col = startCol; col < startCol + 2; col++) {
                if (col > startCol) {
                    sb.append(" ");
                }

                sb.append(matrix[row][col]);
            }

            sb.append(System.lineSeparator());
        }

        sb.append(maxSum);

        System.out.println(sb.toString());
    }

    private static long calcSum(int[][] matrix, int startRow, int startCol) {
        long sum = 0;

        for (int row = startRow; row < startRow + 2; row++) {
            for (int col = startCol; col < startCol + 2; col++) {
                sum += matrix[row][col];
            }
        }

        return sum;
    }
}
