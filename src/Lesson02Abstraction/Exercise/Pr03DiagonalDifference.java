package Lesson02Abstraction.Exercise;

import java.util.Arrays;
import java.util.Scanner;

public class Pr03DiagonalDifference {

    public static void main(String[] args) {
        System.out.println(calcDiagonalDifference(readMatrix()));
    }

    private static int calcDiagonalDifference(int[][] matrix) {
        int size = matrix.length;
        int diff = 0;

        for (int n = 0; n < size; n++) {
            diff += matrix[n][n];
            diff -= matrix[n][size - 1 - n];
        }

        return Math.abs(diff);
    }

    private static int[][] readMatrix() {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());

        int[][] matrix = new int[size][size];

        for (int row = 0; row < size; row++) {
            matrix[row] = Arrays
                    .stream(scanner.nextLine().trim().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        return matrix;
    }
}
