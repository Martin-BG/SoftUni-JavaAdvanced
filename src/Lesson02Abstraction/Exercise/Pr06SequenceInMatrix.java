package Lesson02Abstraction.Exercise;

import java.util.Scanner;

public class Pr06SequenceInMatrix {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine();

        String[][] matrix = new String[rows][cols];

        for (int row = 0; row < rows; row++) {
            matrix[row] = scanner.nextLine().trim().split("\\s+");
        }

        String bestElement = "";
        int bestSeqLength = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int currSeqLength = 0;
                String currentElement = matrix[row][col];

                int currRow = row;

                while (currRow < rows && currentElement.equals(matrix[currRow++][col])) {
                    currSeqLength++;
                }

                if (currSeqLength >= bestSeqLength) {
                    bestSeqLength = currSeqLength;
                    bestElement = currentElement;
                }

                currSeqLength = 0;
                currRow = row;
                int currCol = col;

                while (currRow < rows && currCol < cols && currentElement.equals(matrix[currRow++][currCol++])) {
                    currSeqLength++;
                }

                if (currSeqLength > bestSeqLength) {
                    bestSeqLength = currSeqLength;
                    bestElement = currentElement;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int element = 0; element < bestSeqLength; element++) {
            if (element > 0) {
                sb.append(", ");
            }

            sb.append(bestElement);
        }

        System.out.println(sb);
    }
}
