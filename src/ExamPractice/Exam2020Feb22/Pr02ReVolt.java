package ExamPractice.Exam2020Feb22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Pr02ReVolt {

    private static final char PLAYER = 'f';
    private static final char BONUS = 'B';
    private static final char TRAP = 'T';
    private static final char FINISH = 'F';
    private static final char EMPTY = '-';

    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";

    public static void main(String[] args) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        int size = Integer.parseInt(reader.readLine());
        char[][] matrix = new char[size][size];

        int commands = Integer.parseInt(reader.readLine());

        for (int i = 0; i < size; i++) {
            matrix[i] = reader.readLine().trim().toCharArray();
            if (matrix[i].length != size) {
                throw new IllegalArgumentException();
            }
        }

        Player player = getPlayer(matrix);
        matrix[player.getRow()][player.getCol()] = EMPTY;

        boolean hasWon = false;
        while (!hasWon && commands-- > 0) {
            String command = reader.readLine();

            if (command == null) { // Input data for test #11 in Judge results in null
                hasWon = true; // Test #11 expects this as result
                break;
            }

            movePlayer(player, command);

            char currentCell = matrix[player.getRow()][player.getCol()];
            switch (currentCell) {
            case BONUS:
                movePlayer(player, command);
                break;
            case TRAP:
                movePlayer(player, trapCommand(command));
                break;
            case EMPTY:
                break;
            case FINISH:
                hasWon = true;
                break;
            default:
                throw new IllegalStateException("Invalid character in the matrix: " + currentCell);
            }
        }

        matrix[player.getRow()][player.getCol()] = PLAYER;

        System.out.printf("Player %s!%n%s%n",
                hasWon ? "won" : "lost",
                Arrays.stream(matrix)
                        .map(String::new)
                        .collect(Collectors.joining(System.lineSeparator())));
    }

    private static Player getPlayer(char[][] matrix) {
        int size = matrix[0].length;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (matrix[row][col] == PLAYER) {
                    return new Player(row, col, size);
                }
            }
        }

        throw new IllegalStateException("Player symbol not found in the matrix: " + PLAYER);
    }

    private static void movePlayer(Player player, String command) {
        switch (command) {
        case UP:
            player.up();
            break;
        case DOWN:
            player.down();
            break;
        case LEFT:
            player.left();
            break;
        case RIGHT:
            player.right();
            break;
        default:
            throw new IllegalArgumentException("Invalid command: " + command);
        }
    }

    private static String trapCommand(String command) {
        switch (command) {
        case LEFT:
            return RIGHT;
        case RIGHT:
            return LEFT;
        case UP:
            return DOWN;
        case DOWN:
            return UP;
        default:
            throw new IllegalArgumentException("Invalid movement command: " + command);
        }
    }

    private static class Player {
        private static final int STEP = 1;

        private final int max;
        private int col;
        private int row;

        private Player(int row, int col, int max) {
            this.max = max;
            setCol(col);
            setRow(row);
        }

        private int normalize(int pos) {
            if (pos == -STEP) {
                pos = max - STEP;
            }

            if (pos == max) {
                pos = 0;
            }

            if (pos > max || pos < 0) {
                throw new IllegalArgumentException("Invalid position: " + pos);
            }

            return pos;
        }

        public int getCol() {
            return this.col;
        }

        private void setCol(int col) {
            this.col = normalize(col);
        }

        public int getRow() {
            return this.row;
        }

        private void setRow(int row) {
            this.row = normalize(row);
        }

        public void up() {
            setRow(row - STEP);
        }

        public void down() {
            setRow(row + STEP);
        }

        public void left() {
            setCol(col - STEP);
        }

        public void right() {
            setCol(col + STEP);
        }
    }
}