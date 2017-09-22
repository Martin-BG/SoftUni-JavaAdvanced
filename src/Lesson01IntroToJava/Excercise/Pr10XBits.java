package Lesson01IntroToJava.Excercise;

import java.util.Scanner;

public class Pr10XBits {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] bits = new String[8];

        for (int i = 0; i < 8; i++) {
            bits[i] = String.format("%32s", Integer.toBinaryString(Integer.parseInt(scanner.nextLine())))
                    .replace(' ', '0');
        }

        int count = 0;

        for (int row = 0; row < 8 - 2; row++) {
            for (int col = 0; col < 32 - 2; col++) {
                if (bits[row].substring(col, col + 3).equals("101") &&
                        bits[row + 1].substring(col, col + 3).equals("010") &&
                        bits[row + 2].substring(col, col + 3).equals("101")) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}
