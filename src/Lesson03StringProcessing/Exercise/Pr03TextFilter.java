package Lesson03StringProcessing.Exercise;

import java.util.Scanner;

public class Pr03TextFilter {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] bannedWords = scanner.nextLine().split("[,\\s+]");

        String text = scanner.nextLine();

        for (String bannedWord : bannedWords) {
            text = text.replaceAll(bannedWord,
                    new String(new char[bannedWord.length()]).replace('\0', '*'));
        }

        System.out.println(text);
    }
}
