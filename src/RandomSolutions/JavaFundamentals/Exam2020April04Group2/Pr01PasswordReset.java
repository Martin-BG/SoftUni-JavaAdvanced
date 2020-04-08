package RandomSolutions.JavaFundamentals.Exam2020April04Group2;

import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.IntStream;

public class Pr01PasswordReset {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();

        String input;
        while (!"Done".equals(input = scanner.nextLine())) {
            String[] tokens = input.split("\\s+");
            String command = tokens[0];
            String output;

            switch (command) {
            case "TakeOdd":
                password = IntStream.range(0, password.length())
                        .filter(index -> index % 2 == 1)
                        .mapToObj(password::charAt)
                        .collect(Collector.of(
                                StringBuilder::new,
                                StringBuilder::append,
                                StringBuilder::append,
                                StringBuilder::toString));

                output = password;
                break;
            case "Cut":
                int startIndex = Integer.parseInt(tokens[1]);
                int length = Integer.parseInt(tokens[2]);
                int endIndex = startIndex + length;

                password = new StringBuilder(password)
                        .delete(startIndex, endIndex)
                        .toString();

                output = password;
                break;
            case "Substitute":
                String substring = tokens[1];
                String substitute = tokens[2];

                if (password.contains(substring)) {
                    password = password.replaceAll(substring, substitute);
                    output = password;
                } else {
                    output = "Nothing to replace!";
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown command: " + command);
            }

            System.out.println(output);
        }

        System.out.println("Your password is: " + password);
    }
}