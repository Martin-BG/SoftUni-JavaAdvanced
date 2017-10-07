package Lesson05ObjectsClassesAndAPIs.Exercise;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Pr10CountSymbols {

    public static void main(String[] args) {
        char[] symbolsArr = new Scanner(System.in).nextLine().toCharArray();
        Map<Character, Integer> symbolsMap = new TreeMap<>();

        for (char symbol : symbolsArr) {
            symbolsMap.compute(symbol, (k, v) -> v == null ? 1 : v + 1);
        }

        symbolsMap.forEach((k, v) -> System.out.printf("%c: %d time/s%n", k, v));
    }
}