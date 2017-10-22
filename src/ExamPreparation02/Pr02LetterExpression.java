package ExamPreparation02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pr02LetterExpression {

    public static void main(String[] args) {
        String regex = "(?<symbols>\\D*)?(?<num>\\d+)";
        Pattern pattern = Pattern.compile(regex);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Matcher matcher = pattern.matcher(reader.readLine());
            Long result = 0L;
            while (matcher.find()) {
                Integer number = Integer.valueOf(matcher.group("num"));
                if (matcher.group("symbols").length() % 2 == 0) {
                    result += number;
                } else {
                    result -= number;
                }
            }
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}