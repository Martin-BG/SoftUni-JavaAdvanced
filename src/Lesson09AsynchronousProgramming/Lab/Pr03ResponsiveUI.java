package Lesson09AsynchronousProgramming.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pr03ResponsiveUI {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("n = ");
        int boundary = Integer.parseInt(reader.readLine());

        Runnable task = () -> printPrimes(boundary);
        Thread thread = new Thread(task);
        thread.start();

        while (true) {
            if ("stop".equals(reader.readLine())) {
                thread.interrupt();
                break;
            } else {
                System.out.println("Unknown command");
            }
        }

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printPrimes(int boundary) {
        List<Integer> primes = new ArrayList<>();
        for (int number = 0; number < boundary; number++) {
            if (isPrime(number)) {
                primes.add(number);
            }
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted...");
                break;
            }
        }

        System.out.println(primes.stream()
                .limit(10)
                .collect(Collectors.toList()) + "...");
        System.out.printf("%d primes calculated.", primes.size());
    }

    private static boolean isPrime(int n) {
        // Corner cases
        if (n <= 1) {
            return false;
        }

        if (n <= 3) {
            return true;
        }

        // This is checked so that we can skip
        // middle five numbers in below loop
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }

        int range = (int) Math.sqrt(n) + 1;
        for (int i = 5; i <= range; i = i + 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }
}