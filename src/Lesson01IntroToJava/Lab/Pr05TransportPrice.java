package Lesson01IntroToJava.Lab;

import java.util.Scanner;

public class Pr05TransportPrice {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double distance = Double.parseDouble(scanner.nextLine());
        boolean isDay = "day".equals(scanner.nextLine().trim().toLowerCase());

        double transportPrice = 0d;

        double pricePerKm;

        if (distance < 20d) {
            transportPrice += 0.7d;

            if (isDay) {
                pricePerKm = 0.79d;
            } else {
                pricePerKm = 0.9d;
            }
        } else if (distance < 100d) {
            pricePerKm = 0.09d;
        } else {
            pricePerKm = 0.06d;
        }

        transportPrice += distance * pricePerKm;

        System.out.printf("%.2f", transportPrice);
    }
}