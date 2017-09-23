package Lesson02Abstraction.Excercise;

import java.util.Scanner;

public class Pr08MatrixShuffling {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine();

        String[][] matrix = new String[rows][cols];

        for (int row = 0; row < rows; row++) {
            matrix[row] = scanner.nextLine().split("\\s+");
        }

        String command = scanner.nextLine();

        while (!"END".equals(command)) {
            if (isValid(command, rows, cols)) {
                swapElements(matrix, command);

                printMatrix(matrix);
            } else {
                System.out.println("Invalid input!");
            }

            command = scanner.nextLine();
        }
    }

    private static void printMatrix(String[][] matrix) {

        StringBuilder sb = new StringBuilder();

        for (String[] row : matrix) {
            for (String element : row) {
                sb.append(element);
                sb.append(" ");
            }

            sb.deleteCharAt(sb.length() - 1);
            sb.append(System.lineSeparator());
        }

        System.out.print(sb);
    }

    private static void swapElements(String[][] matrix, String command) {

        String[] tokens = command.split("\\s++");

        int row1 = Integer.parseInt(tokens[1]);
        int col1 = Integer.parseInt(tokens[2]);
        int row2 = Integer.parseInt(tokens[3]);
        int col2 = Integer.parseInt(tokens[4]);

        String temp = matrix[row1][col1];
        matrix[row1][col1] = matrix[row2][col2];
        matrix[row2][col2] = temp;
    }

    private static boolean isValid(String command, int rows, int cols) {

        String[] tokens = command.split("\\s++");

        if (tokens.length != 5 || !"swap".equals(tokens[0])) {
            return false;
        }

        try {
            int row1 = Integer.parseInt(tokens[1]);
            int col1 = Integer.parseInt(tokens[2]);
            int row2 = Integer.parseInt(tokens[3]);
            int col2 = Integer.parseInt(tokens[4]);

            return row1 >= 0 && row1 < rows
                    && col1 >= 0 && col1 < cols
                    && row2 >= 0 && row2 < rows
                    && col2 >= 0 && col2 < cols;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
