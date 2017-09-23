package Lesson01IntroToJava.Exercise;

import java.util.Scanner;

public class Pr04CalculateExpression {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double num1 = scanner.nextDouble();
        double num2 = scanner.nextDouble();
        double num3 = scanner.nextDouble();
        scanner.nextLine();

        double f1 = Math.pow((Math.pow(num1, 2d) + Math.pow(num2, 2d)) / (Math.pow(num1, 2d) - Math.pow(num2, 2d)),
                (num1 + num2 + num3) / Math.sqrt(num3));
        double f2 = Math.pow(Math.pow(num1, 2d) + Math.pow(num2, 2d) - Math.pow(num3, 3d), num1 - num2);
        double diff = Math.abs((num1 + num2 + num3) / 3d - (f1 + f2) / 2d);

        System.out.printf("F1 result: %.2f; F2 result: %.2f; Diff: %.2f%n", f1, f2, diff);
    }
}
