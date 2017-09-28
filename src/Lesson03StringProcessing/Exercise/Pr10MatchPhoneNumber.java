package Lesson03StringProcessing.Exercise;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Pr10MatchPhoneNumber {

    public static void main(String[] args) {

        final String regex = "^\\+359([ -])2\\1\\d{3}\\1\\d{4}$";

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        while (!"end".equals(line)) {
            if (Pattern.matches(regex, line)) {
                System.out.println(line);
            }

            line = scanner.nextLine();
        }
    }
}
