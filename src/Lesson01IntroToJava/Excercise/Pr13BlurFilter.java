package Lesson01IntroToJava.Excercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr13BlurFilter {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        long blurAmount = Long.parseLong(reader.readLine().trim());

        int[] pictureSize = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rows = pictureSize[0];
        int cols = pictureSize[1];

        long[][] pictureMatrix = new long[rows][cols];

        for (int row = 0; row < rows; row++) {
            long[] currentRow = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToLong(Long::parseLong)
                    .toArray();

            System.arraycopy(currentRow, 0, pictureMatrix[row], 0, cols);
        }

        int[] startPoint = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int startRow = Math.max(startPoint[0] - 1, 0);
        int endRow = Math.min(startPoint[0] + 2, rows);
        int startCol = Math.max(startPoint[1] - 1, 0);
        int endCol = Math.min(startPoint[1] + 2, cols);

        for (int row = startRow; row < endRow; row++) {
            for (int col = startCol; col < endCol; col++) {
                pictureMatrix[row][col] += blurAmount;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (col > 0) {
                    sb.append(" ");
                }
                sb.append(pictureMatrix[row][col]);
            }
            sb.append(System.lineSeparator());
        }

        System.out.print(sb.toString());
    }
}
