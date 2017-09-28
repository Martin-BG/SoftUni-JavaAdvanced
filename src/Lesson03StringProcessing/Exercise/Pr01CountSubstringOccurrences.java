package Lesson03StringProcessing.Exercise;

import java.util.Scanner;

public class Pr01CountSubstringOccurrences {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine().toUpperCase();
        String toSearch = scanner.nextLine().toUpperCase();

        int count = 0;
        int lastIndex = 0;

        while (true) {
            lastIndex = text.indexOf(toSearch, lastIndex);

            if (lastIndex == -1) {
                break;
            }

            count++;
            lastIndex++;
        }

        System.out.println(count);
    }
}
