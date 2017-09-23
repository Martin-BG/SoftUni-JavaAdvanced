package Lesson02Abstraction.Exercise;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Pr12ToTheStars {

    public static void main(String[] args) {

        List<StarSystem> galaxy = new LinkedList<>();

        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split("\\s+");

        while (tokens.length > 2) {

            galaxy.add(new StarSystem(tokens[0], Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2])));

            tokens = scanner.nextLine().split("\\s+");
        }

        double currX = Double.parseDouble(tokens[0]);
        double currY = Double.parseDouble(tokens[1]);
        int moves = scanner.nextInt();

        while (moves-- >= 0) {
            boolean inStarSystem = false;

            for (StarSystem starSystem : galaxy) {
                if (starSystem.isWithin(currX, currY)) {
                    System.out.println(starSystem.getName());
                    inStarSystem = true;
                    break;
                }
            }

            currY++;

            if (!inStarSystem) {
                System.out.println("space");
            }
        }
    }

    private static class StarSystem {

        private final String name;
        private final double x;
        private final double y;

        StarSystem(String name, double x, double y) {
            this.name = name.toLowerCase();
            this.x = x - 1d;
            this.y = y - 1d;
        }

        String getName() {
            return this.name;
        }

        boolean isWithin(double x, double y) {
            return x >= this.x && x <= this.x + 2d && y >= this.y && y <= this.y + 2d;
        }
    }
}
