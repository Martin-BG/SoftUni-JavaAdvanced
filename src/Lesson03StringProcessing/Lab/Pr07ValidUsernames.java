package Lesson03StringProcessing.Lab;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pr07ValidUsernames {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Pattern pattern = Pattern.compile("^[\\w-]{3,16}$");
        Matcher matcher;

        while (true) {
            String line = scanner.nextLine();

            if ("END".equals(line)) {
                break;
            }

            matcher = pattern.matcher(line);

            System.out.println(matcher.find() ? "valid" : "invalid");
        }
    }
}
