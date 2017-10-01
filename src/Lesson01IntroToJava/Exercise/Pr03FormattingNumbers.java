package Lesson01IntroToJava.Exercise;

import java.util.Scanner;

public class Pr03FormattingNumbers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num1 = scanner.nextInt();
        double num2 = scanner.nextDouble();
        double num3 = scanner.nextDouble();
        scanner.nextLine();

        String part1 = String.format("%-10X", num1);
        String part2 = String.format("%10s", Integer.toBinaryString(num1)).replace(' ', '0');
        String part3 = String.format("%10.2f", num2);
        String part4 = String.format("%-10.3f", num3);

        System.out.printf("|%s|%s|%s|%s|%n", part1, part2, part3, part4);
    }
}
