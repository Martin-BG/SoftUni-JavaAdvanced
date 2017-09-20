package Lesson01IntroToJava.Lab;

import java.math.BigDecimal;
import java.util.Scanner;

public class Pr03EuroTrip {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final double pricePerKilo = 1.20d;
        final BigDecimal exchangeRate = new BigDecimal("4210500000000");

        double quantity = Double.parseDouble(scanner.nextLine());

        BigDecimal priceInLevs = new BigDecimal(pricePerKilo * quantity);
        BigDecimal marksNeeded = exchangeRate.multiply(priceInLevs);

        System.out.printf("%.2f marks", marksNeeded);
    }
}
