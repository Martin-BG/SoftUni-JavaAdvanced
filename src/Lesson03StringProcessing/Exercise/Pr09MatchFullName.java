package Lesson03StringProcessing.Exercise;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pr09MatchFullName {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Pattern pattern = Pattern.compile("^[A-Z][a-z]+ [A-Z][a-z]+$");
        Matcher matcher;

        while (true) {
            String line = scanner.nextLine();

            if ("end".equals(line)) {
                break;
            }

            matcher = pattern.matcher(line);

            if (matcher.find()) {
                System.out.println(matcher.group());
            }
        }
    }
}
