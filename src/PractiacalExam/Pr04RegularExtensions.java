package PractiacalExam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pr04RegularExtensions {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String text = reader.readLine();

            String input = reader.readLine();
            while (!"Print".equalsIgnoreCase(input)) {
                input = input.replaceAll("%+", "%");

                boolean addToStart = false;
                boolean addToEnd = false;
                if (input.startsWith("%")) {
                    input = input.substring(1);
                    addToStart = true;
                }
                if (input.endsWith("%")) {
                    input = input.substring(0, input.length() - 1);
                    addToEnd = true;
                }
                String[] parts = input.split("%");

                StringBuilder sb = new StringBuilder();
                if (addToStart) {
                    sb.append("\\S*");
                }
                for (int i = 0; i < parts.length - 1; i++) {
                    sb.append(Pattern.quote(parts[i]));
                    sb.append("\\S*");
                }
                sb.append(Pattern.quote(parts[parts.length - 1]));
                if (addToEnd) {
                    sb.append("\\S*");
                }

                Pattern pattern = Pattern.compile(sb.toString());
                Matcher matcher = pattern.matcher(text);
                sb = new StringBuilder();

                int lastEndIndex = 0;
                while (matcher.find()) {
                    int startIndex = matcher.start();
                    sb.append(text.substring(lastEndIndex, startIndex));
                    sb.append(new StringBuilder(matcher.group()).reverse());
                    lastEndIndex = matcher.end();
                }
                sb.append(text.substring(lastEndIndex, text.length()));
                text = sb.toString();

                input = reader.readLine();
            }

            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}