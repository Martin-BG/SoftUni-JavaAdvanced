package Lesson04DataRepresentationAndDataManipulation.Exercise;

import java.util.Scanner;

public class Pr02NestedLoopsToRecursion {

    public static void main(String[] args) {
        int levels = new Scanner(System.in).nextInt();
        buildLevels("", 0, levels);
    }

    private static void buildLevels(String str, int currentLevel, int endLevel) {
        if (currentLevel >= endLevel) {
            System.out.println(str.trim());
            return;
        }
        currentLevel++;
        for (int i = 0; i < endLevel; i++) {
            buildLevels(str + (i + 1) + " ", currentLevel, endLevel);
        }
    }
}