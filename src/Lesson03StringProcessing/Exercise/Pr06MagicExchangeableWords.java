package Lesson03StringProcessing.Exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Pr06MagicExchangeableWords {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        char[] first = scanner.next().toCharArray();
        char[] second = scanner.next().toCharArray();

        if (first.length > second.length) {
            char[] temp = second;
            second = first;
            first = temp;
        }

        Map<Character, Character> charMap = new HashMap<>();

        boolean result = true;

        for (int i = 0; i < first.length; i++) {
            if (!charMap.containsKey(first[i])) {
                if (charMap.containsValue(second[i])) {
                    result = false;
                    break;
                }

                charMap.put(first[i], second[i]);
            } else if (charMap.get(first[i]) != second[i]) {
                result = false;
                break;
            }
        }

        int diff = second.length - first.length;
        if (diff > 0) {
            for (int i = first.length; i < second.length; i++) {
                if (!charMap.containsValue(second[i])) {
                    result = false;
                    break;
                }
            }
        }

        System.out.println(result);
    }
}
