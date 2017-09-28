package Lesson03StringProcessing.Exercise;

import java.util.Scanner;

public class Pr07LettersChangeNumbers {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] codes = scanner.nextLine().split("\\s+");

        double result = 0d;

        for (String code : codes) {
            char first = code.charAt(0);
            char last = code.charAt(code.length() - 1);
            double number = Double.parseDouble(code.substring(1, code.length() - 1));

            if (Character.isLowerCase(first)) {
                number *= first - 'a' + 1;
            } else {
                number /= first - 'A' + 1;
            }

            if (Character.isLowerCase(last)) {
                number += last - 'a' + 1;
            } else {
                number -= last - 'A' + 1;
            }

            result += number;
        }

        System.out.printf("%.2f%n", result);
    }
}
