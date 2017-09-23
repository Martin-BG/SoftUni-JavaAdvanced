package Lesson01IntroToJava.Exercise;

import java.util.Scanner;

public class Pr09ByteParty {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = Integer.parseInt(scanner.nextLine());

        short[] numbers = new short[count];

        for (int i = 0; i < count; i++) {
            numbers[i] = Short.parseShort(scanner.nextLine());
        }

        String command = scanner.nextLine();

        while (!"party over".equals(command.toLowerCase())) {
            executeCommand(numbers, command);

            command = scanner.nextLine();
        }

        for (int num : numbers) {
            System.out.println(num);
        }
    }

    private static void executeCommand(short[] numbers, String commandLine) {
        String[] tokens = commandLine.split("\\s+");

        int command = Integer.parseInt(tokens[0]);
        int position = Integer.parseInt(tokens[1]);

        switch (command) {
            case -1:
                flipBitAtPosition(numbers, position);
                break;
            case 1:
                setBitAtPosition(numbers, position);
                break;
            case 0:
                clearBitAtPosition(numbers, position);
                break;
        }
    }

    private static void clearBitAtPosition(short[] numbers, int position) {
        int mask = ~(1 << position);
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] &= mask;
        }
    }

    private static void setBitAtPosition(short[] numbers, int position) {
        int mask = 1 << position;
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] |= mask;
        }
    }

    private static void flipBitAtPosition(short[] numbers, int position) {
        int mask = 1 << position;
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] ^= mask;
        }
    }
}
