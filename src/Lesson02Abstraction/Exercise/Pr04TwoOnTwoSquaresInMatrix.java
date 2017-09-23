package Lesson02Abstraction.Exercise;

import java.util.Scanner;

public class Pr04TwoOnTwoSquaresInMatrix {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine();

        char[][] matrix = new char[rows][cols];

        for (int row = 0; row < rows; row++) {
            String[] strArr = scanner.nextLine().trim().split("\\s+");

            for (int col = 0; col < cols; col++) {
                matrix[row][col] = strArr[col].charAt(0);
            }
        }

        int matches = 0;

        for (int row = 0; row < rows - 1; row++) {
            for (int col = 0; col < cols - 1; col++) {
                if (matrix[row][col] == matrix[row][col + 1] &&
                        matrix[row][col] == matrix[row + 1][col] &&
                        matrix[row][col] == matrix[row + 1][col + 1]) {
                    matches++;
                }
            }
        }

        System.out.println(matches);
    }
}
