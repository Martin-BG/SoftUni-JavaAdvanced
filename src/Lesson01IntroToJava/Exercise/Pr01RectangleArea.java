package Lesson01IntroToJava.Exercise;

import java.util.Scanner;

public class Pr01RectangleArea {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        scanner.nextLine();

        System.out.printf("%.2f%n", a * b);
    }
}
