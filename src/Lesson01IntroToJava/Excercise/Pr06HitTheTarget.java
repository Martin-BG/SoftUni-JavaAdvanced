package Lesson01IntroToJava.Excercise;

import java.util.Scanner;

public class Pr06HitTheTarget {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int target = Integer.parseInt(scanner.nextLine().trim());

        for (int i = 1; i <= 20; i++) {

            for (int j = 1; j <= 20; j++) {

                Character sign = null;

                if (i + j == target) {
                    sign = '+';
                } else if (i - j == target) {
                    sign = '-';
                }

                if (sign != null) {
                    System.out.printf("%d %c %d = %d%n", i, sign, j, target);
                }
            }
        }
    }
}
