package Lesson02Abstraction.Exercise;

import java.util.Scanner;

public class Pr02MatrixOfPalindromes {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        buildAndPrintPalindromes(rows, cols);
    }

    private static void buildAndPrintPalindromes(int rows, int cols) {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < rows; row++) {
            char edgeSymbol = (char) ('a' + row);

            for (int col = row; col < cols + row; col++) {
                char middleSymbol = (char) ('a' + col);

                if (col > row) {
                    sb.append(" ");
                }

                sb.append(edgeSymbol);
                sb.append(middleSymbol);
                sb.append(edgeSymbol);
            }

            sb.append(System.lineSeparator());
        }

        System.out.println(sb);
    }
}
