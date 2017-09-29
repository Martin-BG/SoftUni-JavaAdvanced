package Lesson04DataRepresentationAndDataManipulation.Lab;

import java.util.Scanner;

public class Pr05RecursiveDrawing {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(System.in);

        int levels = scanner.nextInt();

        recursiveDrawing(sb, levels);

        System.out.print(sb);
    }

    private static void recursiveDrawing(StringBuilder sb, int levels) {
        if (levels == 0) {
            return;
        }

        sb.append(new String(new char[levels]).replaceAll("\0", "*"))
                .append(System.lineSeparator());

        recursiveDrawing(sb, levels - 1);

        sb.append(new String(new char[levels]).replaceAll("\0", "#"))
                .append(System.lineSeparator());
    }
}
