package Lesson04DataRepresentationAndDataManipulation.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pr10SemanticHTML {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> lines = new ArrayList<>();

        while (true) {
            String line = reader.readLine();
            if ("END".equals(line)) {
                break;
            }
            lines.add(line);
        }

        Pattern openingTag = Pattern.compile("(?<start>.*)(?<line>(?<repTag><div(?=\\s))(.*)" +
                "(?<remove>(?<=\\s)(?:id|class)\\s*=\\s*\"(?<tag>[a-z]+)\")(.*>))");
        Pattern closingTag = Pattern.compile("(?<start>.*)</div>\\s+<!--\\s*(?<tag>[a-z]+)\\s*-->");

        for (String line : lines) {
            Matcher openMatch = openingTag.matcher(line);
            if (openMatch.matches()) {
                line = openMatch.group("start") + openMatch.group("line")
                        .replace(openMatch.group("repTag"), "<" + openMatch.group("tag"))
                        .replace(openMatch.group("remove"), "")
                        .replaceAll("\\s+", " ")
                        .replaceAll("\\s+>", ">");
            }

            Matcher closeMatch = closingTag.matcher(line);
            if (closeMatch.matches()) {
                line = closeMatch.group("start") + "</" + closeMatch.group("tag") + ">";
            }

            System.out.println(line);
        }
    }
}
