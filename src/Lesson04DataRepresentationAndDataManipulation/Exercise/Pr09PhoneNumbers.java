package Lesson04DataRepresentationAndDataManipulation.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pr09PhoneNumbers {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String line = reader.readLine();
        while (!"END".equals(line)) {
            sb.append(line);
            line = reader.readLine();
        }

        String regex = "((?<name>[A-Z][A-Za-z]*)[^A-Za-z0-9+]*(?<number>\\+?[0-9][0-9()/.\\- ]+))";
        Matcher matcher = Pattern.compile(regex).matcher(sb.toString());

        boolean found = false;
        sb.setLength(0);
        sb.append("<ol>");
        while (matcher.find()) {
            String number = matcher.group("number").replaceAll("[()/.\\- ]", "");
            if (number.length() < 3 && number.startsWith("+")) {
                continue;
            }
            sb.append("<li><b>").append(matcher.group("name")).append(":</b> ").append(number).append("</li>");
            found = true;
        }
        sb.append("</ol>");

        if (found) {
            System.out.println(sb);
        } else {
            System.out.println("<p>No matches!</p>");
        }
    }
}