package RandomSolutions.JavaFundamentals.L08TextProcessing.MoreExercises;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Pr06WinningTicket {

    private static final String REGEX = "(?=.{20}).*?(?=(?<ch>[@#$^]))(?<match>\\k<ch>{6,}).*(?<=.{10})\\k<match>.*";
    private static final Pattern PATTERN = Pattern.compile(REGEX);
    private static final Pattern SEPARATOR = Pattern.compile("\\s*,\\s*");

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        SEPARATOR.splitAsStream(scan.nextLine().trim())
                .map(ticket -> {
                    if (ticket.length() != 20) {
                        return "invalid ticket";
                    }

                    Matcher matcher = PATTERN.matcher(ticket);
                    if (matcher.matches()) {
                        String match = matcher.group("match");
                        return String.format("ticket \"%s\" - %d%s%s",
                                ticket, match.length(), match.charAt(0),
                                (match.length() == 10) ? " Jackpot!" : "");
                    }

                    return String.format("ticket \"%s\" - no match", ticket);
                })
                .forEach(System.out::println);
    }
}
