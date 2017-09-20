package Lesson01IntroToJava.Lab;

import java.math.BigInteger;
import java.util.Scanner;

public class Pr07ProductOfNumbersNM {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int start = scanner.nextInt();
        int end = scanner.nextInt();
        scanner.nextLine();

        BigInteger result = new BigInteger("1");
        int current = start;

        while (current <= end) {
            result = result.multiply(new BigInteger(Integer.toString(current++)));
        }

        System.out.printf("product[%d..%d] = %s", start, end, result.toString());
    }
}
