package Lesson04DataRepresentationAndDataManipulation.Exercise;

import java.util.Scanner;

public class Pr04CombinationsCount {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());

        System.out.println(getCombinations(n, k));
    }

    private static long getCombinations(int n, int k) {
        long a = 1;
        for (int i = (n - k) + 1; i <= n; i++) {
            a *= i;
        }

        long b = 1;
        for (int i = 1; i <= k; i++) {
            b *= i;
        }

        return a / b;
    }
}
