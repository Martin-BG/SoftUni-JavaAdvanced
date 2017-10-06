package Lesson05ObjectsClassesAndAPIs.Exercise;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class Pr04TruckTourAlgorithmsTest {

    public static void main(String[] args) throws IOException {
        int pumpsCount = 10000 + 2;
        int[] fuelPumps = new int[pumpsCount];
        Deque<Integer> fuelPumpsQueue = new ArrayDeque<>(pumpsCount);

        fuelPumps[pumpsCount - 2] = -1;
        fuelPumps[pumpsCount - 1] = 1;

        for (int i = 0; i < pumpsCount - 2; i++) {
            fuelPumpsQueue.addLast(0);
        }
        fuelPumpsQueue.addLast(-1);
        fuelPumpsQueue.addLast(1);

        System.out.println("Number of elements (fuel pumps): " + pumpsCount);
        System.out.println();

        evaluateStartIndexLinearAlgorithm(pumpsCount, fuelPumps);
        System.out.println();

        evaluateStartIndexQuadraticAlgorithm(fuelPumpsQueue);
        System.out.println();
    }

    private static void evaluateStartIndexQuadraticAlgorithm(Deque<Integer> fuelPumpsQueue) {
        System.out.println("Quadratic Algorithm (queue)...");
        long counter = 0L;
        int startIndex = 0;
        Long ourFuel;
        boolean found;

        long lStartTime = System.nanoTime();
        while (true) {
            ourFuel = 0L;
            found = true;
            for (int fuel : fuelPumpsQueue) {
                counter++;
                ourFuel += fuel;
                if (ourFuel < 0L) {
                    found = false;
                    break;
                }
            }

            if (found) {
                break;
            }
            fuelPumpsQueue.addLast(fuelPumpsQueue.pop());
            startIndex++;
        }
        long lEndTime = System.nanoTime();
        long output = lEndTime - lStartTime;
        System.out.println("Elapsed time in milliseconds: " + output / 1000000);

        System.out.println("Counter: " + counter);
        System.out.println("Start index: " + startIndex);
    }

    private static void evaluateStartIndexLinearAlgorithm(int pumpsCount, int[] fuelPumps) {
        System.out.println("Linear Algorithm (array)...");
        long counter = 0L;
        int startIndex = 0;
        int currentIndex = 0;
        int visitedPumps = 0;
        long currentFuel = 0L;

        long lStartTime = System.nanoTime();
        while (pumpsCount > visitedPumps) {
            counter++;
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
        long lEndTime = System.nanoTime();
        long output = lEndTime - lStartTime;
        System.out.println("Elapsed time in milliseconds: " + output / 1000000);

        System.out.println("Counter: " + counter);
        System.out.println("Start index: " + startIndex);
    }
}