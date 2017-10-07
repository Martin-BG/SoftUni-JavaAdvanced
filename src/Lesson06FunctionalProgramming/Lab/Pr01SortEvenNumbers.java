package Lesson06FunctionalProgramming.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Pr01SortEvenNumbers {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Integer> numbers = new ArrayList<>();
        Arrays.stream(reader.readLine().trim().split("\\s*,\\s+"))
                .mapToInt(Integer::parseInt)
                .filter(num -> num % 2 == 0)
                .forEach(numbers::add);

        if (numbers.size() > 0) {
            System.out.println(Arrays.toString(numbers.toArray()).replaceAll("[\\[\\]]", ""));
            numbers.sort(Comparator.naturalOrder());
            System.out.println(Arrays.toString(numbers.toArray()).replaceAll("[\\[\\]]", ""));
        }
    }
}