package Lesson04DataRepresentationAndDataManipulation.Exercise;

import java.util.Arrays;
import java.util.Scanner;

public class Pr08MultiplyBigNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstNumber = scanner.nextLine().trim().replaceFirst("^0+(?!$)", "");
        int multiplier = Integer.parseInt(scanner.nextLine());

        if (multiplier == 0 || "0".equals(firstNumber)) {
            System.out.println(0);
            return;
        }
        // Another algorithm: http://www.zrzahid.com/multiply-two-big-integers/
        char[] result = new char[firstNumber.length() + 1];
        int carry = 0;
        for (int i = firstNumber.length() - 1; i >= 0; i--) {
            int current = carry + (firstNumber.charAt(i) - '0') * multiplier;
            carry = 0;
            if (current > 9) {
                carry = current / 10;
                current %= 10;
            }
            result[i + 1] = Character.forDigit(current, 10);
        }
        if (carry != 0) {
            result[0] = Character.forDigit(carry, 10);
        }

        System.out.println(Arrays.toString(result).replaceAll("[\\[\\],\0 ]", ""));
    }
}