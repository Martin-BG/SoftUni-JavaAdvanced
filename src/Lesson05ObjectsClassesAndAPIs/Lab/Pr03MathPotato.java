package Lesson05ObjectsClassesAndAPIs.Lab;

import java.util.*;
import java.util.stream.Collectors;

public class Pr03MathPotato {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Collection<String> players = Arrays.stream(scanner.nextLine().split("\\s+"))
                .collect(Collectors.toList());
        Deque<String> queue = new ArrayDeque<>(players);
        int n = Integer.parseInt(scanner.nextLine());

        int cycle = 1;
        while (queue.size() > 1) {
            for (int i = 1; i < n; i++) {
                queue.offer(queue.poll());
            }

            if (!isPrime(cycle)) {
                System.out.println("Removed " + queue.poll());
            } else {
                System.out.println("Prime " + queue.peek());
            }

            cycle++;
        }
        System.out.println("Last is " + queue.poll());
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