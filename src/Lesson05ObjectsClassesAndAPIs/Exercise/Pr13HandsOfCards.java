package Lesson05ObjectsClassesAndAPIs.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Pr13HandsOfCards {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Set<String>> players = new LinkedHashMap<>();

        String line = reader.readLine();

        while (!"JOKER".equals(line)) {
            String[] tokens = line.split(":");
            String playerName = tokens[0];
            Set<String> cards = new HashSet<>(Arrays.asList(tokens[1].trim().split(",\\s+")));

            if (players.containsKey(playerName)) {
                players.get(playerName).addAll(cards);
            } else {
                players.put(playerName, cards);
            }

            line = reader.readLine();
        }

        players.forEach((k, v) -> System.out.printf("%s: %d%n", k, calcScore(v)));
    }

    private static int calcScore(Set<String> cards) {
        int score = 0;
        for (String card : cards) {
            score += getValue(card);
        }
        return score;
    }

    private static int getValue(String card) {
        int type;
        switch (card.charAt(card.length() - 1)) {
        case 'S':
            type = 4;
            break;
        case 'H':
            type = 3;
            break;
        case 'D':
            type = 2;
            break;
        default:
            type = 1;
            break;
        }

        int value;
        switch (card.charAt(0)) {
        case 'J':
            value = 11;
            break;
        case 'Q':
            value = 12;
            break;
        case 'K':
            value = 13;
            break;
        case 'A':
            value = 14;
            break;
        default:
            value = Integer.parseInt(card.substring(0, card.length() - 1));
            break;
        }

        return value * type;
    }
}