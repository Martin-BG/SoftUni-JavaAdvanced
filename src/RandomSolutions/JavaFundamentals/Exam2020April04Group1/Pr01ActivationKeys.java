package RandomSolutions.JavaFundamentals.Exam2020April04Group1;

import java.util.Scanner;
import java.util.function.Function;

public class Pr01ActivationKeys {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String activationKey = scanner.nextLine();

        String input;
        while (!"Generate".equals(input = scanner.nextLine())) {
            String output;

            String[] tokens = input.split(">>>");
            String command = tokens[0];

            switch (command) {
            case "Contains":
                String substring = tokens[1];

                if (activationKey.contains(substring)) {
                    output = String.format("%s contains %s", activationKey, substring);
                } else {
                    output = "Substring not found!";
                }

                break;
            case "Flip":
                String method = tokens[1];
                int startIndex = Integer.parseInt(tokens[2]);
                int endIndex = Integer.parseInt(tokens[3]);

                String replacement = getFlipFunction(method)
                        .apply(activationKey.substring(startIndex, endIndex));

                activationKey = new StringBuilder(activationKey)
                        .replace(startIndex, endIndex, replacement)
                        .toString();

                output = activationKey;
                break;
            case "Slice":
                startIndex = Integer.parseInt(tokens[1]);
                endIndex = Integer.parseInt(tokens[2]);

                activationKey = new StringBuilder(activationKey)
                        .delete(startIndex, endIndex)
                        .toString();

                output = activationKey;
                break;
            default:
                throw new UnsupportedOperationException("Unknown command: " + command);
            }

            System.out.println(output);
        }

        System.out.println("Your activation key is: " + activationKey);
    }

    private static Function<String, String> getFlipFunction(String method) {
        switch (method) {
        case "Upper":
            return String::toUpperCase;
        case "Lower":
            return String::toLowerCase;
        default:
            throw new UnsupportedOperationException("Unknown flip method: " + method);
        }
    }
}
