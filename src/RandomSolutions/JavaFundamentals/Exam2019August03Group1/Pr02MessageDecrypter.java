package RandomSolutions.JavaFundamentals.Exam2019August03Group1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pr02MessageDecrypter {

    public static void main(String[] args) throws IOException {
        String regex = "^(?<t>[$%])(?<tag>[A-Z][a-z]{2,})\\k<t>: \\[(?<first>\\d+)]\\|\\[(?<second>\\d+)]\\|\\[(?<third>\\d+)]\\|$";
        Pattern pattern = Pattern.compile(regex);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        int inputsCount = Integer.parseInt(reader.readLine());
        while (inputsCount-- > 0) {
            String input = reader.readLine();
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                String tag = matcher.group("tag");
                char first = (char) Integer.parseInt(matcher.group("first"));
                char second = (char) Integer.parseInt(matcher.group("second"));
                char third = (char) Integer.parseInt(matcher.group("third"));
                System.out.printf("%s: %c%c%c%n", tag, first, second, third);
            } else {
                System.out.println("Valid message not found!");
            }
        }
    }
}
