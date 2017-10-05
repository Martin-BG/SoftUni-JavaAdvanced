package Lesson05ObjectsClassesAndAPIs.Lab;

import java.util.*;
import java.util.stream.Collectors;

public class Pr02HotPotato {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Collection<String> players = Arrays.stream(scanner.nextLine().split("\\s+"))
                .collect(Collectors.toList());
        Deque<String> queue = new ArrayDeque<>(players);
        int n = Integer.parseInt(scanner.nextLine());

        while (queue.size() > 1) {
            for (int i = 1; i < n; i++) {
                queue.offer(queue.poll());
            }
            System.out.println("Removed " + queue.poll());
        }
        System.out.println("Last is " + queue.poll());
    }
}