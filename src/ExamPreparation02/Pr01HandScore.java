package ExamPreparation02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Pr01HandScore {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] cards = reader.readLine().toUpperCase().split("\\s+");
            String lastSuit = "";
            List<Integer> sequenceOfSameSuit = new ArrayList<>(13);
            int score = 0;
            for (String card : cards) {
                String currentSuit = card.substring(card.length() - 1);
                int value = getCardValue(card.substring(0, card.length() - 1));
                if (lastSuit.equals(currentSuit)) {
                    sequenceOfSameSuit.add(value);
                } else {
                    score += sequenceOfSameSuit.stream().reduce(0, (a, b) -> a + b) * sequenceOfSameSuit.size();
                    sequenceOfSameSuit.clear();
                    sequenceOfSameSuit.add(value);
                    lastSuit = currentSuit;
                }
            }
            score += sequenceOfSameSuit.stream().reduce(0, (a, b) -> a + b) * sequenceOfSameSuit.size();
            System.out.println(score);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static int getCardValue(String card) {
        switch (card) {
        case "J":
            return 12;
        case "Q":
            return 13;
        case "K":
            return 14;
        case "A":
            return 15;
        default:
            return Integer.parseInt(card);
        }
    }
}