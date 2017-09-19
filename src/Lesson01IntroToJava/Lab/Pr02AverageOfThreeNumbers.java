package Lesson01IntroToJava.Lab;

import java.util.Scanner;

public class Pr02AverageOfThreeNumbers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double first = scanner.nextDouble();
        double second = scanner.nextDouble();
        double third = scanner.nextDouble();
        scanner.nextLine();

        double average = (first + second + third) / 3d;

        System.out.printf("%.2f", average);
    }
}
