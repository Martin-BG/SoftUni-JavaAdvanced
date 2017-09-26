package Lesson03StringProcessing.Lab;

import java.util.Scanner;

public class Pr03ParseTags {

    public static void main(String[] args) {

        final String upperCaseStart = "<upcase>";
        final String upperCaseEnd = "</upcase>";

        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        while (text.contains(upperCaseStart)) {
            int startIndex = text.indexOf(upperCaseStart);
            int endIndex = text.indexOf(upperCaseEnd);
            String uppercaseText = text.substring(startIndex + upperCaseStart.length(), endIndex);

            text = text.replaceFirst(upperCaseStart, "");
            text = text.replaceFirst(upperCaseEnd, "");
            text = text.replaceFirst(uppercaseText, uppercaseText.toUpperCase());
        }

        System.out.println(text);
    }
}
