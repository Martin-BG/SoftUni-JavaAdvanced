package ExamPractice.Exam2020Feb22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Pr01LootBox {

    public static void main(String[] args) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        LinkedList<Integer> firstBox = Arrays
                .stream(reader.readLine().trim().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(LinkedList::new));

        LinkedList<Integer> secondBox = Arrays
                .stream(reader.readLine().trim().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(LinkedList::new));

        int loot = 0;

        while (!firstBox.isEmpty() && !secondBox.isEmpty()) {
            int secondItem = secondBox.removeLast();
            int currentLoot = firstBox.getFirst() + secondItem;
            if (currentLoot % 2 == 0) {
                loot += currentLoot;
                firstBox.removeFirst();
            } else {
                firstBox.add(secondItem);
            }
        }

        System.out.printf("%s lootbox is empty%n", firstBox.isEmpty() ? "First" : "Second");
        System.out.printf("Your loot was %s Value: %d%n", loot < 100 ? "poor..." : "epic!", loot);
    }
}