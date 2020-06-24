package RandomSolutions.JavaFundamentals.L03Arrays.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class Pr10LadyBugs {

    private static final Pattern SPACES_PATTERN = Pattern.compile("\\s+");
    private static final Pattern ARRAYS_PATTERN = Pattern.compile("[\\[\\],]");

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8));


        int fieldSize = Integer.parseInt(reader.readLine());
        int[] field = new int[fieldSize];


        reader.lines()
                .limit(1L)
                .flatMapToInt(ladyBugIndexes -> SPACES_PATTERN
                        .splitAsStream(ladyBugIndexes)
                        .mapToInt(Integer::parseInt))
                .filter(index -> index >= 0 && index < fieldSize)
                .forEach(ladyBugIndex -> field[ladyBugIndex] = 1);

        Consumer<String> ladyBugCommandExecutor = ladyBugCommandExecutor(field);

        reader.lines()
                .takeWhile(line -> !"end".equals(line))
                .forEachOrdered(ladyBugCommandExecutor);

        System.out.println(ARRAYS_PATTERN
                .matcher(Arrays.toString(field))
                .replaceAll(""));
    }

    private static Consumer<String> ladyBugCommandExecutor(int[] field) {
        int fieldSize = field.length;

        return command -> {
            String[] tokens = SPACES_PATTERN.split(command);
            int ladyBugIndex = Integer.parseInt(tokens[0]);

            if (ladyBugIndex >= 0 && ladyBugIndex < fieldSize && field[ladyBugIndex] == 1) {
                field[ladyBugIndex] = 0;

                String direction = tokens[1];
                int flyLength = Integer.parseInt(tokens[2]);

                if ("left".equals(direction)) {
                    flyLength = -flyLength;
                }

                int newIndex = ladyBugIndex;

                do {
                    newIndex += flyLength;
                } while (newIndex >= 0 && newIndex < fieldSize && field[newIndex] != 0);

                if (newIndex >= 0 && newIndex < fieldSize) {
                    field[newIndex] = 1;
                }
            }
        };
    }
}
