package Lesson05ObjectsClassesAndAPIs.Exercise;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class Pr01BasicStackOperations {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> stack = new ArrayDeque<>();

        int[] orders = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        int elementsToPush = orders[0];
        int elementsToPop = orders[1];
        int valueToCheck = orders[2];

        for (int i = 0; i < elementsToPush; i++) {
            stack.push(scanner.nextInt());
        }

        for (int i = 0; i < elementsToPop; i++) {
            stack.pop();
        }

        if (stack.contains(valueToCheck)) {
            System.out.println(true);
        } else if (stack.isEmpty()) {
            System.out.println(0);
        } else {
            int min = stack.pop();

            while (stack.size() > 0) {
                int num = stack.pop();
                min = (min > num) ? num : min;
            }

            System.out.println(min);
        }
    }
}