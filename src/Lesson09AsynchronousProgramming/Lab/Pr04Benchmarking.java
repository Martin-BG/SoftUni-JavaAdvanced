package Lesson09AsynchronousProgramming.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Pr04Benchmarking {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("n = ");
        int boundary = Integer.parseInt(reader.readLine());

        List<Integer> numbers = new ArrayList<>(boundary);

        for (int i = 0; i < boundary; i++) {
            numbers.add(i);
        }

        long start = System.nanoTime();

        ExecutorService es = Executors.newFixedThreadPool(1);

        Future[] results = new Future[boundary];

        for (int i = 0; i < numbers.size(); i++) {
            Integer number = numbers.get(i);
            Future<Boolean> future = es.submit(() -> isPrime(number));
            results[i] = future;
        }

        es.awaitTermination(100L, TimeUnit.MILLISECONDS);

        long total = System.nanoTime() - start;
        System.out.println("Execution time: " + total);
        es.shutdown();

        for (Future result : results) {
            System.out.println(result.get());
        }
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