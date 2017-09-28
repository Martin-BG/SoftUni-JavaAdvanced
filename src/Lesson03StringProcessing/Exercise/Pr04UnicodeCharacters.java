package Lesson03StringProcessing.Exercise;

import java.util.Scanner;

public class Pr04UnicodeCharacters {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            sb.append(String.format("\\u%04x", input.codePointAt(i)));
        }

        System.out.println(sb);
    }
}
