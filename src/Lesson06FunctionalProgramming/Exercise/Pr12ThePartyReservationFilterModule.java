package Lesson06FunctionalProgramming.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Predicate;

public class Pr12ThePartyReservationFilterModule {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] names = reader.readLine().split("\\s+");
        boolean[] hidden = new boolean[names.length];

        while (true) {
            String[] command = reader.readLine().trim().split(";");

            if ("Print".equals(command[0])) {
                break;
            }

            Predicate<String> isMatch = getMatchPredicate(command[1], command[2]);

            if (isMatch == null) {
                continue;
            }

            boolean hide = "Add filter".equals(command[0]);

            for (int i = 0; i < names.length; i++) {
                if (isMatch.test(names[i])) {
                    hidden[i] = hide;
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            if (!hidden[i]) {
                System.out.printf("%s ", names[i]);
            }
        }
    }

    private static Predicate<String> getMatchPredicate(String command, String argument) {
        switch (command) {
        case "Starts with":
            return name -> name.startsWith(argument);
        case "Ends with":
            return name -> name.endsWith(argument);
        case "Length":
            return name -> name.length() == Integer.parseInt(argument);
        case "Contains":
            return name -> name.contains(argument);
        default:
            return null;
        }
    }
}