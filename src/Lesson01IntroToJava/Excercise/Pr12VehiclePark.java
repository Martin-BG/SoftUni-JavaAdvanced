package Lesson01IntroToJava.Excercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Pr12VehiclePark {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] allVehicles = reader.readLine().split("\\s+");

        List<Vehicle> vehicles = new LinkedList<>();

        for (String vehicleSignature : allVehicles) {
            vehicles.add(new Vehicle(vehicleSignature));
        }

        int vehiclesSold = 0;

        String order = reader.readLine();

        while (!"End of customers!".equals(order)) {
            VehicleType type = FindType(order);
            int seats = Integer.parseInt(order.split("\\s+")[2]);
            boolean sold = false;

            for (int i = 0; i < vehicles.size(); i++) {
                Vehicle vehicle = vehicles.get(i);

                if (vehicle.getType() == type && vehicle.getSeats() == seats) {
                    vehiclesSold++;
                    System.out.printf("Yes, sold for %d$%n", vehicle.getCost());
                    vehicles.remove(i);
                    sold = true;
                    break;
                }
            }

            if (!sold) {
                System.out.println("No");
            }

            order = reader.readLine();
        }

        boolean addSeparator = false;

        System.out.print("Vehicles left:");

        for (Vehicle vehicle : vehicles) {
            if (addSeparator) {
                System.out.print(",");
            }

            System.out.printf(" %s", vehicle);

            addSeparator = true;
        }

        System.out.printf("%nVehicles sold: %d%n", vehiclesSold);
    }

    private static VehicleType FindType(String vehicleSignature) {
        VehicleType type;

        switch (vehicleSignature.toLowerCase().charAt(0)) {
            case 'c':
                type = VehicleType.CAR;
                break;
            case 'v':
                type = VehicleType.VAN;
                break;
            case 'b':
                type = VehicleType.BUS;
                break;
            default:
                type = VehicleType.CAR;
                break;
        }

        return type;
    }

    enum VehicleType {
        CAR,
        VAN,
        BUS
    }

    static class Vehicle {
        VehicleType type;
        int seats;
        long cost;
        String signature;

        Vehicle(String vehicleSignature) {
            this.signature = vehicleSignature;
            this.type = FindType(vehicleSignature);
            this.seats = Integer.parseInt(vehicleSignature.substring(1));
            this.cost = vehicleSignature.charAt(0) * this.seats;
        }

        VehicleType getType() {
            return this.type;
        }

        int getSeats() {
            return this.seats;
        }

        long getCost() {
            return this.cost;
        }

        @Override
        public String toString() {
            return this.signature;
        }
    }
}
