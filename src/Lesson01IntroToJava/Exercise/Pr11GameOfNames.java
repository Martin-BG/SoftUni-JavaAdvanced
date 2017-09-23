package Lesson01IntroToJava.Exercise;

import java.util.Scanner;

public class Pr11GameOfNames {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int playersCount = Integer.parseInt(scanner.nextLine());

        Player bestPlayer = null;

        for (int i = 0; i < playersCount; i++) {

            String name = scanner.nextLine().trim();

            int startScore = Integer.parseInt(scanner.nextLine());

            Player currentPlayer = new Player(name, startScore);

            if (bestPlayer == null || bestPlayer.getScore() < currentPlayer.getScore()) {
                bestPlayer = currentPlayer;
            }
        }

        System.out.println(bestPlayer);
    }

    private static class Player {
        String name;
        int score;

        Player(String name, int startScore) {
            this.name = name;
            this.score = startScore;
            calculatePlayerPoints();
        }

        private void calculatePlayerPoints() {
            for (int i = 0; i < this.name.length(); i++) {
                int codeValue = (int) this.name.charAt(i);

                if (codeValue % 2 == 0) {
                    this.score += codeValue;
                } else {
                    this.score -= codeValue;
                }
            }
        }

        int getScore() {
            return this.score;
        }

        @Override
        public String toString() {
            return String.format("The winner is %s - %d points", this.name, this.score);
        }
    }
}
