package Lesson04DataRepresentationAndDataManipulation.Lab;

import java.util.Scanner;

public class Pr04RecursiveFactorial {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        System.out.println(factorial(number));
    }

    private static long factorial(int number) {
        return (number == 0) ? 1L : number * factorial(number - 1);
    }
}
