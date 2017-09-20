package Lesson01IntroToJava.Excercise;

import java.util.Scanner;

public class Pr02TriangleArea {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();
        double x3 = scanner.nextDouble();
        double y3 = scanner.nextDouble();
        scanner.nextLine();

        // http://www.mathopenref.com/coordtrianglearea.html
        double area = Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2d);

        System.out.println((int) area);
    }
}
