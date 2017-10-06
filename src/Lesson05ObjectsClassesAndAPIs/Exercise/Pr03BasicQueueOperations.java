package Lesson05ObjectsClassesAndAPIs.Exercise;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class Pr03BasicQueueOperations {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> queue = new ArrayDeque<>();

        int[] orders = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        int elementsToEnqueue = orders[0];
        int elementsToDequeue = orders[1];
        int valueToCheck = orders[2];

        for (int i = 0; i < elementsToEnqueue; i++) {
            queue.addLast(scanner.nextInt());
        }

        for (int i = 0; i < elementsToDequeue; i++) {
            queue.removeFirst();
        }

        if (queue.contains(valueToCheck)) {
            System.out.println(true);
        } else if (queue.isEmpty()) {
            System.out.println(0);
        } else {
            int min = queue.pop();

            while (queue.size() > 0) {
                int num = queue.pop();
                min = (min > num) ? num : min;
            }

            System.out.println(min);
        }
    }
}