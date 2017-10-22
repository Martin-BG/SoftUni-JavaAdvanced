package PractiacalExam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Pr02LittleAlchemy {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            ArrayDeque<Integer> stones = new ArrayDeque<>();
            Deque<Integer> gold = new ArrayDeque<>();

            Arrays.stream(reader.readLine().trim().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .forEachOrdered(stones::addLast);

            String input = reader.readLine();
            while (!"Revision".equalsIgnoreCase(input)) {
                String[] tokens = input.split("\\s+");
                int value = Integer.parseInt(tokens[2]);

                if ("Apply".equalsIgnoreCase(tokens[0])) {
                    for (int i = 0; i < value; i++) {
                        if (!stones.isEmpty()) {
                            int stone = stones.pop();
                            stone--;
                            if (stone > 0) {
                                stones.addLast(stone);
                            } else {
                                gold.addLast(stone);
                            }
                        }
                    }
                } else if ("Air".equalsIgnoreCase(tokens[0]) && !gold.isEmpty()) {
                    stones.addLast(value);
                    gold.pop();
                }

                input = reader.readLine();
            }

            System.out.println(stones.toString().replaceAll("[\\[,\\]]", ""));
            System.out.println(gold.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}