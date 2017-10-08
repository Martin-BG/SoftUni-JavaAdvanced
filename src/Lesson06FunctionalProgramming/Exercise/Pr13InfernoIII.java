package Lesson06FunctionalProgramming.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class Pr13InfernoIII {

    public static void main(String[] args) throws IOException {
        Map<String, Predicate<Integer>> commands = new LinkedHashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] gemPowers = Arrays
                .stream(reader.readLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        boolean continueToRead = true;
        while (continueToRead) {
            String[] tokens = reader.readLine().toLowerCase().trim().split(";");

            switch (tokens[0]) {
            case "forge":
                continueToRead = false;
                break;
            case "exclude":
                commands.put(tokens[1] + tokens[2], getCommand(tokens[1], tokens[2], gemPowers));
                break;
            case "reverse":
                commands.remove(tokens[1] + tokens[2]);
                break;
            default:
                break;
            }
        }

        boolean[] hiddenGems = new boolean[gemPowers.length];
        for (int i = 0; i < gemPowers.length; i++) {
            if (hiddenGems[i]) {
                continue;
            }

            for (Predicate<Integer> predicate : commands.values()) {
                if (predicate.test(i)) {
                    hiddenGems[i] = true;
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hiddenGems.length; i++) {
            if (!hiddenGems[i]) {
                sb.append(gemPowers[i]).append(" ");
            }
        }
        System.out.println(sb.toString().trim());
    }

    private static Predicate<Integer> getCommand(String command, String targetStr, int[] gemPowers) {
        int targetSum = Integer.parseInt(targetStr);
        switch (command) {
        case "sum left":
            return x -> (x > 0 ? gemPowers[x - 1] : 0) + gemPowers[x] == targetSum;
        case "sum right":
            return x -> (x < gemPowers.length - 1 ? gemPowers[x + 1] : 0) + gemPowers[x] == targetSum;
        case "sum left right":
        default:
            return x -> (x > 0 ? gemPowers[x - 1] : 0) +
                    (x < gemPowers.length - 1 ? gemPowers[x + 1] : 0) + gemPowers[x] == targetSum;
        }
    }
}