package PracticalExamRetake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pr01SonarFence {

    private static final int BITS = 32;
    private static final String END_COMMAND = "Reprogram";

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while (!END_COMMAND.equalsIgnoreCase(input = reader.readLine())) {
            long num = Long.parseLong(input);

            long result = 0L;
            long maskFirst = 1L << BITS - 1;
            long maskSecond = 1L << BITS - 2;

            while (maskSecond >= 1L) {
                boolean first = (num & maskFirst) != 0L;
                boolean second = (num & maskSecond) != 0L;
                int shiftStep = 1;

                if (first == second) {
                    if (!first) {
                        result |= maskFirst;
                        result |= maskSecond;
                    }

                    shiftStep = 2;

                } else {
                    if (first) {
                        result |= maskFirst;
                    }
                }

                maskFirst >>= shiftStep;
                maskSecond >>= shiftStep;
            }

            if (maskFirst == 1L && (num & maskFirst) != 0L) {
                result |= maskFirst;
            }

            System.out.println(result);
        }
    }
}
