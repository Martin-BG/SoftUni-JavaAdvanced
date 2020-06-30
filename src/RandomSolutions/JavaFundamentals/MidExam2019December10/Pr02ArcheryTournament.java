package RandomSolutions.JavaFundamentals.MidExam2019December10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Pr02ArcheryTournament {

    private static final Pattern TARGETS_PATTERN = Pattern.compile("\\|");

    private static final Matcher SHOOT_MATCHER = Pattern
            .compile("^Shoot (?<direction>Left|Right)@(?<start>\\d+)@(?<length>\\d+)$")
            .matcher("");

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8));

        List<Integer> targets = TARGETS_PATTERN
                .splitAsStream(reader.readLine())
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        ToIntFunction<String> commandProcessor = commandProcessor(targets);
        Predicate<String> notGameOver = command -> !"Game over".equals(command);

        int points = reader.lines()
                .takeWhile(notGameOver)
                .mapToInt(commandProcessor)
                .sum();

        System.out.println(targets.stream().map(String::valueOf).collect(Collectors.joining(" - ")));
        System.out.printf("Iskren finished the archery tournament with %d points!", points);
    }

    private static ToIntFunction<String> commandProcessor(List<Integer> targets) {
        return command -> {
            int pointsScored = 0;

            if ("Reverse".equals(command)) {
                Collections.reverse(targets);
            } else if (SHOOT_MATCHER.reset(command).matches()) {
                String direction = SHOOT_MATCHER.group("direction");
                int startIndex = Integer.parseInt(SHOOT_MATCHER.group("start"));
                int length = Integer.parseInt(SHOOT_MATCHER.group("length"));

                pointsScored = shootAtTarget(targets, direction, startIndex, length);
            } else {
                // throw new IllegalArgumentException("Invalid command: " + command);
            }

            return pointsScored;
        };
    }

    private static int shootAtTarget(List<Integer> targets,
                                     String direction, int startIndex, int length) {
        int pointsScored = 0;

        if (startIndex >= 0 && startIndex < targets.size()) {
            length %= targets.size();

            int offset = "Right".equals(direction) ? length : (targets.size() - length);

            int targetIndex = (startIndex + offset) % targets.size();

            Integer targetPoints = targets.get(targetIndex);
            pointsScored = Math.min(5, targetPoints);
            targetPoints -= pointsScored;
            targets.set(targetIndex, targetPoints);
        }

        return pointsScored;
    }
}
