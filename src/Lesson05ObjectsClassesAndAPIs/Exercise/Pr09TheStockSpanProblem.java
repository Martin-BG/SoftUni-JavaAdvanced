package Lesson05ObjectsClassesAndAPIs.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Pr09TheStockSpanProblem {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int days = Integer.parseInt(reader.readLine());
        int[] prices = new int[days];

        for (int i = 0; i < days; i++) {
            prices[i] = Integer.parseInt(reader.readLine());
        }

        int[] spans = calculateSpan(prices, days);

        StringBuilder sb = new StringBuilder();

        for (int span : spans) {
            sb.append(span).append(System.lineSeparator());
        }

        System.out.println(sb);
    }

    private static int[] calculateSpan(int price[], int days) {
        // http://www.geeksforgeeks.org/the-stock-span-problem/
        int[] spans = new int[days];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        spans[0] = 1;

        for (int i = 1; i < days; i++) {
            while (!stack.isEmpty() && price[stack.peek()] <= price[i]) {
                stack.pop();
            }
            spans[i] = (stack.isEmpty()) ? (i + 1) : (i - stack.peek());
            stack.push(i);
        }

        return spans;
    }
}