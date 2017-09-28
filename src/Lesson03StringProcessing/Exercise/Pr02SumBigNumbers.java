package Lesson03StringProcessing.Exercise;

import java.util.Scanner;

public class Pr02SumBigNumbers {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        char[] num1 = scanner.nextLine().trim().replaceFirst("^0+", "").toCharArray();
        char[] num2 = scanner.nextLine().trim().replaceFirst("^0+", "").toCharArray();

        StringBuilder sb = new StringBuilder();

        int index1 = num1.length - 1;
        int index2 = num2.length - 1;
        int maxLength = Math.max(index1, index2);
        int carry = 0;

        for (int i = 0; i <= maxLength; i++) {
            int sum = carry;
            carry = 0;

            if (index1 >= 0) {
                sum += num1[index1--] - '0';
            }

            if (index2 >= 0) {
                sum += num2[index2--] - '0';
            }

            if (sum > 9) {
                carry = 1;
                sum -= 10;
            }

            sb.append(sum);
        }

        if (carry == 1) {
            sb.append(carry);
        }

        System.out.println(sb.reverse());
    }
}
