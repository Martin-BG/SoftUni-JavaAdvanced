package Lesson01IntroToJava.Excercise;

import java.util.Scanner;

public class Pr07CharacterMultiplier {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str1 = scanner.next();
        String str2 = scanner.next();
        scanner.nextLine();

        int position = 0;
        long total = 0L;

        while (true) {
            Integer current = null;

            if (str1.length() > position) {
                current = (int) str1.charAt(position);
            }

            if (str2.length() > position) {
                if (current != null) {
                    current *= (int) str2.charAt(position);
                } else {
                    current = (int) str2.charAt(position);
                }
            }

            if (current == null) {
                break;
            }

            total += current;

            position++;
        }

        System.out.println(total);
    }
}

