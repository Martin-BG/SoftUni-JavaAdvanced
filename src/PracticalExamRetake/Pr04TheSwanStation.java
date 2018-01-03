package PracticalExamRetake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Pr04TheSwanStation {

    private static final String DELIMITER = "\\s+";
    private static final int DESIRED_NUMBERS = 6;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Long> delimiters = Arrays
                .stream(reader.readLine().trim().split(DELIMITER))
                .map(Long::parseLong)
                .collect(Collectors.toCollection(LinkedList::new));

        Deque<Long> numbers = Arrays
                .stream(reader.readLine().trim().split(DELIMITER))
                .map(Long::parseLong)
                .collect(Collectors.toCollection(ArrayDeque::new));

        long[] result = new long[DESIRED_NUMBERS];
        int matches = 0;

        long delimiter = delimiters.remove(0);

        while (matches < DESIRED_NUMBERS && !numbers.isEmpty()) {
            long number = numbers.pollFirst();

            if (number % delimiter == 0L) {
                result[matches++] = number;

                if (delimiters.isEmpty()) {
                    break;
                }

                delimiter = delimiters.remove(0);
            } else {
                numbers.addLast(++number);
            }
        }

        System.out.println(Arrays.toString(result).replaceAll("[\\[\\]]", ""));
    }
}
