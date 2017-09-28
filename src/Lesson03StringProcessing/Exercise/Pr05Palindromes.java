package Lesson03StringProcessing.Exercise;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Pr05Palindromes {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] words = scanner.nextLine().split("[\\s+.,?!]+");

        Set<String> palindromes = new TreeSet<>();

        for (String word : words) {
            if (word.equals(new StringBuffer(word).reverse().toString())) {
                palindromes.add(word);
            }
        }

        System.out.println(palindromes);
    }
}
