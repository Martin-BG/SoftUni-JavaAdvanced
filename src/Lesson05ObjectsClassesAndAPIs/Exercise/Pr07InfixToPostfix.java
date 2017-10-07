package Lesson05ObjectsClassesAndAPIs.Exercise;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Pr07InfixToPostfix {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] expression = scanner.nextLine().split("\\s+");
        StringBuilder result = new StringBuilder();
        Deque<String> stack = new ArrayDeque<>();
        // https://en.wikipedia.org/wiki/Shunting-yard_algorithm
        for (String element : expression) {
            switch (element) {
            case "+":
            case "-":
            case "*":
            case "/":
                while (!stack.isEmpty() && isPrecedenceEqualOrGreater(stack.peek(), element)) {
                    result.append(stack.pop()).append(" ");
                }
                stack.push(element);
                break;
            case "(":
                stack.push(element);
                break;
            case ")":
                while (!"(".equals(stack.peek())) {
                    result.append(stack.pop()).append(" ");
                }
                stack.pop();
                break;
            default:
                result.append(element).append(" ");
                break;
            }
        }

        while (stack.size() > 0) {
            result.append(stack.pop()).append(" ");
        }

        System.out.println(result.toString().trim());
    }

    private static boolean isPrecedenceEqualOrGreater(String first, String second) {
        switch (first) {
        case "(":
            return false;
        case "/":
        case "*":
            return true;
        case "+":
        case "-":
            return "+".equals(second) || "-".equals(second);
        default:
            return false;
        }
    }
}