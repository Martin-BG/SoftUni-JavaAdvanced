package Lesson02Abstraction.Lab;

import java.util.Arrays;
import java.util.Scanner;

public class Pr02EncryptSortAndPrintArray {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int elements = Integer.parseInt(scanner.nextLine());

        int[] values = new int[elements];

        for (int i = 0; i < elements; i++) {
            values[i] = calcValue(scanner.nextLine().trim());
        }

        Arrays.sort(values);

        for (int value : values) {
            System.out.println(value);
        }
    }

    private static int calcValue(String word) {
        int value = 0;
        int length = word.length();

        for (int i = 0; i < length; i++) {
            char ch = word.charAt(i);

            value += isVowel(ch) ? ch * length : ch / length;
        }

        return value;
    }

    private static boolean isVowel(char c) {
        final String vowels = "aeiouAEIOU";
        return vowels.contains(Character.toString(c));
    }
}
