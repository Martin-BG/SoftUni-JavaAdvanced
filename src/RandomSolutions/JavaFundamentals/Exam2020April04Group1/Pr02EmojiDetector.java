package RandomSolutions.JavaFundamentals.Exam2020April04Group1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Pr02EmojiDetector {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();

        long threshold = input.chars()
                .filter(Character::isDigit)
                .mapToObj(Character::getNumericValue)
                .map(Long::valueOf)
                .reduce((a, b) -> a * b)
                .orElse(0L);

        Pattern pattern = Pattern.compile("(?<emoji>(?<symbols>[:]{2}|[*]{2})(?<name>[A-Z][a-z]{2,})\\k<symbols>)");
        Matcher matcher = pattern.matcher(input);

        int count = 0;
        List<String> emojis = new ArrayList<>();
        while (matcher.find()) {
            count++;
            matcher.group("name").chars()
                    .mapToObj(Long::valueOf)
                    .reduce(Long::sum)
                    .filter(current -> current.compareTo(threshold) >= 0)
                    .ifPresent(sum -> emojis.add(matcher.group("emoji")));
        }

        System.out.println(String.format(
                "Cool threshold: %d%n%d emojis found in the text. The cool ones are:%n%s%n",
                threshold,
                count,
                emojis.stream().collect(Collectors.joining(System.lineSeparator()))));
    }
}
