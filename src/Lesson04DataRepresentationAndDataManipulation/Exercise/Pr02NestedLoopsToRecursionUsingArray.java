package Lesson04DataRepresentationAndDataManipulation.Exercise;

import java.util.Scanner;

public class Pr02NestedLoopsToRecursionUsingArray {

    public static void main(String[] args) {
        int levels = new Scanner(System.in).nextInt();
        int[] numbers = new int[levels];
        buildLevels(numbers, 0, levels);
    }

    private static void buildLevels(int[] numbers, int currentLevel, int endLevel) {
        if (currentLevel >= endLevel) {
            printNumbers(numbers);
            return;
        }

        for (int i = 1; i <= endLevel; i++) {
            numbers[currentLevel] = i;
            buildLevels(numbers, currentLevel + 1, endLevel);
        }
    }

    private static void printNumbers(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        for (int number : numbers) {
            sb.append(number).append(" ");
        }
        sb.delete(sb.length() - 1, sb.length());
        System.out.println(sb);
    }
}