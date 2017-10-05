package Lesson05ObjectsClassesAndAPIs.Lab;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Pr01MatchingBrackets {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < expression.length(); i++) {
            switch (expression.charAt(i)) {
            case '(':
                stack.push(i);
                break;
            case ')':
                System.out.println(expression.substring(stack.pop(), i + 1));
                break;
            default:
                break;
            }
        }
    }
}