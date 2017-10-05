package Lesson05ObjectsClassesAndAPIs.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Pr04ParkingLot {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Set<String> parkingLot = new HashSet<>();

        String input = reader.readLine();

        while (!"END".equals(input)) {
            String[] orders = input.split(",\\s+");

            if ("IN".equals(orders[0])) {
                parkingLot.add(orders[1]);
            } else {
                parkingLot.remove(orders[1]);
            }

            input = reader.readLine();
        }

        if (parkingLot.isEmpty()) {
            System.out.println("Parking Lot is Empty");
        } else {
            for (String car : parkingLot) {
                System.out.println(car);
            }
        }
    }
}