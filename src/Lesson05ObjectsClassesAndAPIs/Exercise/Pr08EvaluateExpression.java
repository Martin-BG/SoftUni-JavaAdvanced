package Lesson05ObjectsClassesAndAPIs.Exercise;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Pr08EvaluateExpression {

    public static void main(String[] args) {
        String[] postfix = infixToPostfix(new Scanner(System.in).nextLine());
        System.out.printf("%.2f%n", evaluatePostfix(postfix));
    }

    private static double evaluatePostfix(String[] postfix) {
        Deque<Double> stack = new ArrayDeque<>();
        double num1;
        double num2;
        // http://btechsmartclass.com/DS/U2_T6.html
        for (String element : postfix) {
            switch (element) {
            case "+":
                num1 = stack.pop();
                num2 = stack.pop();
                stack.push(num2 + num1);
                break;
            case "-":
                num1 = stack.pop();
                num2 = stack.pop();
                stack.push(num2 - num1);
                break;
            case "*":
                num1 = stack.pop();
                num2 = stack.pop();
                stack.push(num2 * num1);
                break;
            case "/":
                num1 = stack.pop();
                num2 = stack.pop();
                stack.push(num2 / num1);
                break;
            default:
                stack.push(Double.parseDouble(element));
                break;
            }
        }

        return stack.pop();
    }

    private static String[] infixToPostfix(String expr) {
        String[] expression = expr.split("\\s+");
        StringBuilder result = new StringBuilder();
        Deque<String> stack = new ArrayDeque<>();

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

        return result.toString().trim().split("\\s+");
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