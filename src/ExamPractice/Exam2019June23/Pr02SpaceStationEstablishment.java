package ExamPractice.Exam2019June23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Pr02SpaceStationEstablishment {

    public static void main(String[] args) throws IOException {
        Engine engine = initEngine();
        engine.start();
    }

    private static Engine initEngine() throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        int universeSize = Integer.parseInt(reader.readLine());

        List<Visitable> objects = new ArrayList<>();
        Spaceship spaceship = new Spaceship();
        parseUniverse(reader, universeSize, spaceship, objects);

        Universe universe = new Universe(universeSize, objects);
        return new Engine(spaceship, universe, reader);
    }

    private static void parseUniverse(final BufferedReader reader,
                                      int universeSize,
                                      final Spaceship spaceship,
                                      final List<Visitable> objects) throws IOException {
        BlackHole firstBlackHole = null;
        for (int row = 0; row < universeSize; row++) {
            char[] chars = reader.readLine().trim().toUpperCase().toCharArray();
            for (int col = 0; col < universeSize; col++) {
                switch (chars[col]) {
                case '-':
                    break;
                case 'S':
                    spaceship.setCoordinate(new Coordinate(row, col));
                    break;
                case 'O':
                    BlackHole blackHole = new BlackHole(new Coordinate(row, col));
                    objects.add(blackHole);
                    if (firstBlackHole != null) {
                        firstBlackHole.setExit(blackHole);
                        blackHole.setExit(firstBlackHole);
                    }
                    firstBlackHole = blackHole;
                    break;
                default:
                    Star star = new Star(new Coordinate(row, col), chars[col] - '0');
                    objects.add(star);
                    break;
                }
            }
        }
    }

    interface Actor {
        char getSymbol();

        Pointable getCoordinate();
    }

    interface Visitable extends Actor {
        void visit(Spaceship spaceship);

        boolean isVisited();
    }

    interface Pointable {
        int getRow();

        int getCol();
    }

    private static final class Engine {
        private final Spaceship spaceship;
        private final Universe universe;
        private final BufferedReader reader;

        private Engine(Spaceship spaceship, Universe universe, BufferedReader reader) {
            this.spaceship = spaceship;
            this.universe = universe;
            this.reader = reader;
        }

        public void start() {
            while (true) {
                moveShip();

                if (universe.intoTheVoid(spaceship.getCoordinate())) {
                    System.out.println("Bad news, the spaceship went to the void.");
                    System.out.println("Star power collected: " + spaceship.getPower());
                    System.out.println(universe.asString(spaceship));
                    break;
                }

                universe.tick(spaceship);

                if (spaceship.hasEnoughEnergy()) {
                    System.out.println("Good news! Stephen succeeded in collecting enough star power!");
                    System.out.println("Star power collected: " + spaceship.getPower());
                    System.out.println(universe.asString(spaceship));
                    break;
                }
            }
        }

        private void moveShip() {
            try {
                String command = reader.readLine().trim().toUpperCase();
                switch (command) {
                case "UP":
                    spaceship.up();
                    break;
                case "RIGHT":
                    spaceship.right();
                    break;
                case "DOWN":
                    spaceship.down();
                    break;
                case "LEFT":
                    spaceship.left();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid move command: " + command);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class Universe {
        private final Map<? super Pointable, ? extends Visitable> objects;
        private final int size;

        public Universe(int size, Collection<? extends Visitable> objects) {
            this.size = size;
            this.objects = objects.stream()
                    .collect(Collectors.toMap(Actor::getCoordinate, o -> o));
        }

        public void tick(Spaceship spaceship) {
            objects.computeIfPresent(spaceship.getCoordinate(),
                    (k, v) -> {
                        v.visit(spaceship);
                        return v;
                    });
        }

        public String asString(Spaceship spaceship) {
            char[][] map = new char[size][size];
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    map[row][col] = '-';
                }
            }

            objects.forEach((k, v) -> {
                if (!v.isVisited()) {
                    setSymbol(map, v);
                }
            });

            if (!intoTheVoid(spaceship.getCoordinate())) {
                setSymbol(map, spaceship);
            }

            StringBuilder sb = new StringBuilder();
            for (int row = 0; row < size; row++) {
                sb.append(map[row]).append(System.lineSeparator());
            }

            return sb.toString();
        }

        private static <T extends Actor> void setSymbol(char[][] map, T actor) {
            map[actor.getCoordinate().getRow()][actor.getCoordinate().getCol()] = actor.getSymbol();
        }

        public boolean intoTheVoid(Pointable obj) {
            return obj.getRow() < 0 || obj.getRow() >= size ||
                    obj.getCol() < 0 || obj.getCol() >= size;
        }
    }

    private static class Coordinate implements Pointable {
        private final int row;
        private final int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(getRow(), getCol());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Coordinate that = (Coordinate) o;
            return getRow() == that.getRow() &&
                    getCol() == that.getCol();
        }

        @Override
        public int getRow() {
            return row;
        }

        @Override
        public int getCol() {
            return col;
        }
    }

    private static class Star implements Visitable {
        private final Coordinate coordinate;
        private final int power;
        private boolean visited;

        public Star(Coordinate coordinate, int power) {
            this.coordinate = coordinate;
            this.power = power;
            visited = false;
        }

        @Override
        public void visit(Spaceship spaceship) {
            if (visited) {
                return;
            }

            spaceship.addPower(power);
            visited = true;
        }

        @Override
        public boolean isVisited() {
            return visited;
        }

        @Override
        public char getSymbol() {
            return Character.forDigit(power, 10);
        }

        @Override
        public Pointable getCoordinate() {
            return coordinate;
        }
    }

    private static class BlackHole implements Visitable {
        private final Coordinate coordinate;
        private BlackHole exit;
        private boolean visited;

        public BlackHole(Coordinate coordinate) {
            this.coordinate = coordinate;
        }

        public void setExit(BlackHole exit) {
            this.exit = exit;
        }

        @Override
        public void visit(Spaceship spaceship) {
            if (visited) {
                return;
            }

            spaceship.setCoordinate(exit.getCoordinate());
            exit.visited = true;
            visited = true;
        }

        @Override
        public boolean isVisited() {
            return visited;
        }

        @Override
        public char getSymbol() {
            return 'O';
        }

        @Override
        public Coordinate getCoordinate() {
            return coordinate;
        }
    }

    private static class Spaceship implements Actor {
        private static final int REQUIRED_STAR_POWER = 50;
        private Coordinate coordinate;
        private int power;

        public void addPower(int power) {
            this.power += power;
        }

        public int getPower() {
            return power;
        }

        public boolean hasEnoughEnergy() {
            return power >= REQUIRED_STAR_POWER;
        }

        public void up() {
            this.setCoordinate(new Coordinate(coordinate.row - 1, coordinate.col));
        }

        public void down() {
            this.setCoordinate(new Coordinate(coordinate.row + 1, coordinate.col));
        }

        public void left() {
            this.setCoordinate(new Coordinate(coordinate.row, coordinate.col - 1));
        }

        public void right() {
            this.setCoordinate(new Coordinate(coordinate.row, coordinate.col + 1));
        }

        @Override
        public char getSymbol() {
            return 'S';
        }

        @Override
        public Coordinate getCoordinate() {
            return coordinate;
        }

        public void setCoordinate(Coordinate coordinate) {
            this.coordinate = coordinate;
        }
    }
}
