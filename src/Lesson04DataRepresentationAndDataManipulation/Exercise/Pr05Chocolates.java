package Lesson04DataRepresentationAndDataManipulation.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr05Chocolates {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int packetsCount = Integer.parseInt(reader.readLine());
        int[] packets = Arrays.stream(reader.readLine().split(",\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        int students = Integer.parseInt(reader.readLine());

        Arrays.sort(packets);

        int min = Integer.MAX_VALUE;
        for (int i = packetsCount - 1; i >= students - 1; i--) {
            int current = packets[i] - packets[i - students + 1];
            if (current < min) {
                min = current;
            }
        }

        System.out.printf("Min Difference is %d.%n", min);
    }
}
