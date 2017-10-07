package Lesson06FunctionalProgramming.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Function;

public class Pr04AddVAT {

    public static void main(String[] args) throws IOException {
        Function<String, Double> addVAT = x -> Double.parseDouble(x) * 1.2;
        System.out.println("Prices with VAT:");
        Arrays.stream(new BufferedReader(new InputStreamReader(System.in)).readLine().split(",\\s+"))
                .forEach(x -> System.out.println(String.format("%.2f", addVAT.apply(x)).replaceAll("\\.", ",")));
    }
}