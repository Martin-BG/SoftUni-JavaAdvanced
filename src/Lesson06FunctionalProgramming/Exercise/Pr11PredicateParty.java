package Lesson06FunctionalProgramming.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class Pr11PredicateParty {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> names = new LinkedList<>(Arrays.asList(reader.readLine().split("\\s+")));

        while (true) {
            String[] command = reader.readLine().trim().split("\\s+");

            if ("Party!".equals(command[0])) {
                break;
            }

            Predicate<String> isMatch = getMatchPredicate(command[1], command[2]);

            if (isMatch == null) {
                continue;
            }

            switch (command[0]) {
            case "Remove":
                names.removeIf(isMatch);
                break;
            case "Double":
                for (int i = 0; i < names.size(); i++) {
                    if (isMatch.test(names.get(i))) {
                        names.add(i, names.get(i));
                        i++;
                    }
                }
                break;
            }
        }

        if (names.size() > 0) {
            System.out.printf("%s are going to the party!", names.toString().replaceAll("[\\[\\]]", ""));
        } else {
            System.out.println("Nobody is going to the party!");
        }
    }

    private static Predicate<String> getMatchPredicate(String command, String argument) {
        switch (command) {
        case "StartsWith":
            return name -> name.startsWith(argument);
        case "EndsWith":
            return name -> name.endsWith(argument);
        case "Length":
            return name -> name.length() == Integer.parseInt(argument);
        default:
            return null;
        }
    }
}