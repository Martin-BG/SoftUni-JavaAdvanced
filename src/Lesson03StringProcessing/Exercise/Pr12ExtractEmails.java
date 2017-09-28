package Lesson03StringProcessing.Exercise;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pr12ExtractEmails {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        String regex = "(?<=^|\\s)([A-Za-z0-9][A-Za-z0-9._-]*[A-Za-z0-9])@" +
                "([A-Za-z][A-Za-z-]*(\\.[A-Za-z]+(-?[A-Za-z]+)*)+)(?=$|,|\\s|\\.)";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}