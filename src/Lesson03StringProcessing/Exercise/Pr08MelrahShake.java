package Lesson03StringProcessing.Exercise;

import java.util.Scanner;

public class Pr08MelrahShake {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        StringBuilder text = new StringBuilder(scanner.nextLine());
        StringBuilder pattern = new StringBuilder(scanner.nextLine());

        while (true) {
            int patternLength = pattern.length();
            int first = text.indexOf(pattern.toString());
            int last = text.lastIndexOf(pattern.toString());

            if (first != -1 && last != -1 && first + patternLength <= last && patternLength > 0) {
                System.out.println("Shaked it.");
                text = text.delete(last, last + patternLength);
                text = text.delete(first, first + patternLength);
                pattern = pattern.deleteCharAt(patternLength / 2);
            } else {
                System.out.println("No shake.");
                break;
            }
        }

        System.out.println(text);
    }
}