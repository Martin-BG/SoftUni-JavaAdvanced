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
                Pattern pattern = Pattern.compile(getRegex(input));
                Matcher matcher = pattern.matcher(text);

                // Solution 1
                StringBuffer stringBuffer = new StringBuffer();
                while (matcher.find()) {
                    matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement(new StringBuilder(matcher.group()).reverse().toString()));
                }
                matcher.appendTail(stringBuffer);
                text = stringBuffer.toString();

//                // Solution 2
//                StringBuilder sb = new StringBuilder();
//                int lastEndIndex = 0;
//                while (matcher.find()) {
//                    int startIndex = matcher.start();
//                    sb.append(text.substring(lastEndIndex, startIndex));
//                    sb.append(new StringBuilder(matcher.group()).reverse());
//                    lastEndIndex = matcher.end();
//                }
//                sb.append(text.substring(lastEndIndex, text.length()));
//                text = sb.toString();

//                // Judge pass solution
//                while (matcher.find()) {
//                    text = text.replace(matcher.group(), new StringBuilder(matcher.group()).reverse().toString());
//                }

                input = reader.readLine();
            }

            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getRegex(String input) {
        final String specialSymbolReplacement = "\\S*";
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
            sb.append(specialSymbolReplacement);
        }

        for (int i = 0; i < parts.length - 1; i++) {
            sb.append(Pattern.quote(parts[i]));
            sb.append(specialSymbolReplacement);
        }
        sb.append(Pattern.quote(parts[parts.length - 1]));

        if (addToEnd) {
            sb.append(specialSymbolReplacement);
        }

        return sb.toString();
    }
}