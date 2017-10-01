package Lesson02Abstraction.Exercise;

import java.util.Scanner;

public class Pr01FillTheMatrix {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split(", ");

        int size = Integer.parseInt(tokens[0]);

        char pattern = tokens[1].trim().toUpperCase().charAt(0);

        int[][] matrix = fillMatrixByPattern(pattern, size);

        printMatrix(matrix);
    }

    private static int[][] fillMatrixByPattern(char pattern, int size) {
        int[][] matrix = new int[size][size];

        switch (pattern) {
        case 'A':
            patternA(matrix);
            break;
        case 'B':
            patternB(matrix);
            break;
        default:
            break;
        }

        return matrix;
    }

    private static void patternA(int[][] matrix) {
        int size = matrix.length;
        int nextValue = 1;

        for (int col = 0; col < size; col++) {
            for (int row = 0; row < size; row++) {
                matrix[row][col] = nextValue++;
            }
        }
    }

    private static void patternB(int[][] matrix) {
        int size = matrix.length;
        int nextValue = 1;

        for (int col = 0; col < size; col++) {
            for (int row = 0; row < size; row++) {
                matrix[row][col] = nextValue++;
            }

            if (++col < size) {
                for (int row = size - 1; row >= 0; row--) {
                    matrix[row][col] = nextValue++;
                }
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        int size = matrix.length;

        for (int[] row : matrix) {
            for (int col = 0; col < size; col++) {
                if (col > 0) {
                    sb.append(" ");
                }

                sb.append(row[col]);
            }
            sb.append(System.lineSeparator());
        }

        System.out.println(sb);
    }
}
