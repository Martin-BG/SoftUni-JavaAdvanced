package Lesson03StringProcessing.Lab;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pr05VowelCount {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        Pattern pattern = Pattern.compile("[AEIOUYaeiouy]");
        Matcher matcher = pattern.matcher(text);

        int count = 0;

        while (matcher.find()) {
            count++;
        }

        System.out.printf("Vowels: %d%n", count);
    }
}
