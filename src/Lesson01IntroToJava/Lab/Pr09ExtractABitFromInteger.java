package Lesson01IntroToJava.Lab;

import java.util.Scanner;

public class Pr09ExtractABitFromInteger {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = scanner.nextInt();
        int index = scanner.nextInt();
        scanner.nextLine();

        int bit = (number >> index) & 1;

        System.out.println(bit);
    }
}
