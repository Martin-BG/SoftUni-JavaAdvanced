package Lesson06FunctionalProgramming.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Consumer;

public class Pr02KnightsOfHonor {

    public static void main(String[] args) throws IOException {
        Consumer<String> print = name -> System.out.printf("Sir %s%n", name);
        Arrays.stream(new BufferedReader(new InputStreamReader(System.in)).readLine().trim().split("\\s+"))
                .forEach(print);
    }
}