package Lesson02Abstraction.Excercise;

import java.util.Scanner;

public class Pr06SequenceInMatrixSaneSolution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine();

        String[][] matrix = new String[rows][cols];
        int[][] sequences = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            matrix[row] = scanner.nextLine().trim().split("\\s+");
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int currSeqLength = 1;
                String currentElement = matrix[row][col];

                int currCol = col + 1;

                while (currCol < cols && currentElement.equals(matrix[row][currCol++])) {
                    currSeqLength++;
                }

                for (int el = 0; el < currSeqLength; el++) {
                    if (sequences[row][col + el] < currSeqLength) {
                        sequences[row][col + el] = currSeqLength;
                    }
                }

                currSeqLength = 1;
                int currRow = row + 1;

                while (currRow < rows && currentElement.equals(matrix[currRow++][col])) {
                    currSeqLength++;
                }

                for (int el = 0; el < currSeqLength; el++) {
                    if (sequences[row + el][col] < currSeqLength) {
                        sequences[row + el][col] = currSeqLength;
                    }
                }

                currSeqLength = 1;
                currCol = col + 1;
                currRow = row + 1;

                while (currRow < rows && currCol < cols && currentElement.equals(matrix[currRow++][currCol++])) {
                    currSeqLength++;
                }

                for (int el = 0; el < currSeqLength; el++) {
                    if (sequences[row + el][col + el] < currSeqLength) {
                        sequences[row + el][col + el] = currSeqLength;
                    }
                }

                currSeqLength = 1;
                currCol = col - 1;
                currRow = row + 1;

                while (currRow < rows && currCol > 0 && currentElement.equals(matrix[currRow++][currCol--])) {
                    currSeqLength++;
                }

                for (int el = 0; el < currSeqLength; el++) {
                    if (sequences[row + el][col - el] < currSeqLength) {
                        sequences[row + el][col - el] = currSeqLength;
                    }
                }
            }
        }

        String bestElement = "";
        int bestSeqLength = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (sequences[row][col] >= bestSeqLength) {
                    bestSeqLength = sequences[row][col];
                    bestElement = matrix[row][col];
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
