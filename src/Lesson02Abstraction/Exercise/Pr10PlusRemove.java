package Lesson02Abstraction.Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pr10PlusRemove {

    public static void main(String[] args) {

        List<char[]> data = readData();

        plusRemover(data);

        printResult(data);
    }

    private static void plusRemover(List<char[]> data) {

        int rows = data.size();

        List<boolean[]> toRemove = new ArrayList<>(rows);
        for (char[] row : data) {
            toRemove.add(new boolean[row.length]);
        }

        for (int row = 0; row < rows - 2; row++) {
            char[] topRow = data.get(row);
            char[] middleRow = data.get(row + 1);
            char[] bottomRow = data.get(row + 2);

            int endCol = middleRow.length - 1;

            if (topRow.length < endCol) {
                endCol = topRow.length;
            }

            if (bottomRow.length < endCol) {
                endCol = bottomRow.length;
            }

            for (int col = 1; col < endCol; col++) {
                char check = Character.toLowerCase(topRow[col]);

                if (check == Character.toLowerCase(middleRow[col]) &&
                        check == Character.toLowerCase(bottomRow[col]) &&
                        check == Character.toLowerCase(middleRow[col - 1]) &&
                        check == Character.toLowerCase(middleRow[col + 1])) {
                    toRemove.get(row)[col] = true;
                    toRemove.get(row + 1)[col] = true;
                    toRemove.get(row + 1)[col + 1] = true;
                    toRemove.get(row + 1)[col - 1] = true;
                    toRemove.get(row + 2)[col] = true;
                }
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < toRemove.get(row).length; col++) {
                if (toRemove.get(row)[col]) {
                    data.get(row)[col] = '\0';
                }
            }
        }
    }

    private static List<char[]> readData() {

        Scanner scanner = new Scanner(System.in);

        List<char[]> data = new ArrayList<>();

        String line = scanner.nextLine();

        while (!"END".equals(line)) {
            data.add(line.toCharArray());
            line = scanner.nextLine();
        }

        return data;
    }


    private static void printResult(List<char[]> data) {

        StringBuilder sb = new StringBuilder();

        for (char[] chars : data) {
            for (char ch : chars) {
                if (ch != '\0') {
                    sb.append(ch);
                }
            }

            sb.append(System.lineSeparator());
        }

        System.out.print(sb);
    }
}
