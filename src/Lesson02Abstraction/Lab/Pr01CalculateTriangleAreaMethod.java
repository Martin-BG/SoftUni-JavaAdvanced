package Lesson02Abstraction.Lab;

import java.util.Scanner;

public class Pr01CalculateTriangleAreaMethod {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double base = scanner.nextDouble();
        double height = scanner.nextDouble();

        System.out.printf("Area = %.2f", calcTriangleArea(base, height));
    }

    private static double calcTriangleArea(double base, double height) {
        return base * height / 2d;
    }
}
