package RandomSolutions.JavaFundamentals.Exam2020April04Group1;

import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Pr02EmojiDetectorWithRegex {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        List<String> emojiList = Pattern.compile("(:{2}|\\*{2})[A-Z][a-z]{2,}\\1")
                .matcher(text)
                .results()
                .map(MatchResult::group)
                .collect(Collectors.toList());

        long coolThreshold = Pattern.compile("\\d")
                .matcher(text)
                .results()
                .mapToLong(matchResult -> Long.parseLong(matchResult.group()))
                .reduce(1L, (a, b) -> a * b);

        System.out.printf("Cool threshold: %d%n", coolThreshold);
        System.out.printf("%d emojis found in the text. The cool ones are:%n", emojiList.size());

        long finalCoolThreshold = coolThreshold;
        emojiList.stream()
                .filter(emoji -> emoji.substring(2, emoji.length() - 2).chars().sum() >= finalCoolThreshold)
                .forEach(System.out::println);
    }
}
