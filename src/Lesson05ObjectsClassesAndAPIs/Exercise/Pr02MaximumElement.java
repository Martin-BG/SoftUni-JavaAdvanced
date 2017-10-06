package Lesson05ObjectsClassesAndAPIs.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Pr02MaximumElement {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> stackMax = new ArrayDeque<>();

        int lines = Integer.parseInt(reader.readLine());

        while (lines-- > 0) {
            String[] tokens = reader.readLine().split("\\s+");

            switch (tokens[0]) {
            case "1": {
                int num = Integer.parseInt(tokens[1]);
                if (stackMax.isEmpty() || stackMax.peek() <= num) {
                    stackMax.push(num);
                }
                stack.push(num);
                break;
            }
            case "2": {
                int num = stack.pop();
                if (num == stackMax.peek()) {
                    stackMax.pop();
                }
                break;
            }
            case "3": {
                if (!stackMax.isEmpty()) {
                    sb.append(stackMax.peek()).append(System.lineSeparator());
                }
                break;
            }
            default:
                break;
            }
        }

        System.out.print(sb);
    }
}