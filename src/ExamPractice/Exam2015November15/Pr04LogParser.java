package ExamPractice.Exam2015November15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pr04LogParser {

    public static void main(String[] args) {
        Map<String, Map<String, List<String>>> log = getInputData();
        StringBuilder result = new StringBuilder();
        log.entrySet()
                .stream()
                .sorted(projectsComparator())
                .forEachOrdered(project -> putProjectDetails(project, result));
        System.out.print(result);
    }

    private static Map<String, Map<String, List<String>>> getInputData() {
        Map<String, Map<String, List<String>>> log = new TreeMap<>();

        String regex = "\\[\"(?<project>.+)\"].*?\\[\"(?<type>.+)\"].*?\\[\"(?<message>.+)\"]";
        Pattern pattern = Pattern.compile(regex);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine();
            while (!"end".equalsIgnoreCase(input)) {
                Matcher matcher = pattern.matcher(input);
                if (matcher.find()) {
                    if (!log.containsKey(matcher.group("project"))) {
                        log.put(matcher.group("project"), new HashMap<>());
                        log.get(matcher.group("project")).put("Critical", new LinkedList<>());
                        log.get(matcher.group("project")).put("Warning", new LinkedList<>());
                    }
                    log.get(matcher.group("project"))
                            .get(matcher.group("type"))
                            .add(matcher.group("message"));
                }
                input = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return log;
    }

    private static void putProjectDetails(Map.Entry<String, Map<String, List<String>>> project, StringBuilder stringBuilder) {
        int errors = project.getValue().get("Critical").size();
        int warnings = project.getValue().get("Warning").size();
        stringBuilder.append(String.format("%s:%n", project.getKey()));
        stringBuilder.append(String.format("Total Errors: %d%n", errors + warnings));
        stringBuilder.append(String.format("Critical: %d%n", errors));
        stringBuilder.append(String.format("Warnings: %d%n", warnings));

        project.getValue().get("Critical").sort(messagesComparator());
        putMessages(project.getValue().get("Critical"), "Critical", stringBuilder);

        project.getValue().get("Warning").sort(messagesComparator());
        putMessages(project.getValue().get("Warning"), "Warning", stringBuilder);
        stringBuilder.append(System.lineSeparator());
    }

    private static void putMessages(List<String> messages, String type, StringBuilder stringBuilder) {
        stringBuilder.append(String.format("%s Messages:%n", type));
        if (messages.isEmpty()) {
            stringBuilder.append(String.format("--->None%n"));
        } else {
            for (String message : messages) {
                stringBuilder.append(String.format("--->%s%n", message));
            }
        }
    }

    private static Comparator<Map.Entry<String, Map<String, List<String>>>> projectsComparator() {
        return (a, b) -> {
            int aMessages = a.getValue().get("Critical").size() + a.getValue().get("Warning").size();
            int bMessages = b.getValue().get("Critical").size() + b.getValue().get("Warning").size();
            int cmp = -Integer.compare(aMessages, bMessages);
            if (cmp == 0) {
                cmp = a.getKey().compareTo(b.getKey());
            }
            return cmp;
        };
    }

    private static Comparator<String> messagesComparator() {
        return (a, b) -> {
            if (a.length() != b.length()) {
                return Integer.compare(a.length(), b.length());
            }
            return a.compareTo(b);
        };
    }
}