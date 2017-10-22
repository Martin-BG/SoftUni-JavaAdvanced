package ExamPreparation01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Pr04Highscore {

    public static void main(String[] args) throws IOException {
        Map<String, Player> players = new LinkedHashMap<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();

        while (!"osu!".equals(input.toLowerCase())) {
            String[] tokens = input.split("\\s+");
            Integer playerOneScore = Integer.valueOf(tokens[0]);
            Integer playerTwoScore = Integer.valueOf(tokens[2]);
            tokens = tokens[1].split("<->");
            String playerOne = tokens[0];
            String playerTwo = tokens[1];

            if (playerOneScore > playerTwoScore) {
                playerOneScore -= playerTwoScore;
                playerTwoScore = -playerOneScore;
            } else if (playerTwoScore > playerOneScore) {
                playerTwoScore -= playerOneScore;
                playerOneScore = -playerTwoScore;
            } else {
                playerOneScore = 0;
                playerTwoScore = 0;
            }

            if (!players.containsKey(playerOne)) {
                players.put(playerOne, new Player(playerOne));
            }

            if (!players.containsKey(playerTwo)) {
                players.put(playerTwo, new Player(playerTwo));
            }

            players.get(playerOne).addGame(playerOneScore, playerTwo);
            players.get(playerTwo).addGame(playerTwoScore, playerOne);

            input = reader.readLine();
        }

//      players.values().stream().sorted(Comparator.reverseOrder()).forEachOrdered(System.out::print); //Slow
        Object[] sorted = players.values().toArray();
        Arrays.sort(sorted, Collections.reverseOrder());

        for (Object o : sorted) {
            System.out.print(o.toString());
        }
    }

    private static class Player implements Comparable<Player> {
        private String name;
        private Long score;
        private List<String> games;

        Player(String name) {
            this.name = name;
            score = 0L;
            games = new LinkedList<>();
        }

        void addGame(Integer score, String opponent) {
            this.score += score;
            games.add(String.format("*   %s <-> %d", opponent, score));
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(name).append(" - (").append(score).append(")").append(System.lineSeparator());
            for (String game : games) {
                sb.append(game).append(System.lineSeparator());
            }
            return sb.toString();
        }

        @Override
        public int compareTo(Player other) {
            return score.compareTo(other.score);
        }
    }
}