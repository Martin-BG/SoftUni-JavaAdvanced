package RandomSolutions.JavaFundamentals.Exam2020April04Group1;

import java.math.BigInteger;
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

        BigInteger threshold = input.chars()
                .filter(Character::isDigit)
                .mapToObj(Character::getNumericValue)
                .map(BigInteger::valueOf)
                .reduce(BigInteger::multiply)
                .orElse(BigInteger.ZERO);

        Pattern pattern = Pattern.compile("(?<emoji>(?<symbols>[:]{2}|[*]{2})(?<name>[A-Z][a-z]{2,})\\k<symbols>)");
        Matcher matcher = pattern.matcher(input);

        int count = 0;
        List<String> emojis = new ArrayList<>();
        while (matcher.find()) {
            count++;
            matcher.group("name").chars()
                    .mapToObj(BigInteger::valueOf)
                    .reduce(BigInteger::add)
                    .filter(current -> current.compareTo(threshold) >= 0)
                    .ifPresent(x -> emojis.add(matcher.group("emoji")));
        }

        System.out.println(String.format(
                "Cool threshold: %d%n%d emojis found in the text. The cool ones are:%n%s%n",
                threshold,
                count,
                emojis.stream().collect(Collectors.joining(System.lineSeparator()))));
    }
}
