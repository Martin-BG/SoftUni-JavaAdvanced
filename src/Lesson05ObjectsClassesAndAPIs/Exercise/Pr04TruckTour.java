package Lesson05ObjectsClassesAndAPIs.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pr04TruckTour {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int pumpsCount = Integer.parseInt(reader.readLine());
        int[] fuelPumps = new int[pumpsCount];

        for (int i = 0; i < pumpsCount; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            fuelPumps[i] = Integer.parseInt(tokens[0]) - Integer.parseInt(tokens[1]);
        }

        int startIndex = 0;
        int currentIndex = 0;
        int visitedPumps = 0;
        long currentFuel = 0L;
        while (pumpsCount > visitedPumps) {
            if (fuelPumps[currentIndex] + currentFuel < 0L) {
                if (visitedPumps > 0) {
                    visitedPumps--;
                    currentFuel -= fuelPumps[startIndex];
                } else {
                    currentIndex++;
                }
                startIndex++;
            } else {
                currentFuel += fuelPumps[currentIndex];
                visitedPumps++;
                currentIndex++;
                if (currentIndex >= pumpsCount) {
                    currentIndex = 0;
                }
            }
        }

        System.out.print(startIndex);
    }
}