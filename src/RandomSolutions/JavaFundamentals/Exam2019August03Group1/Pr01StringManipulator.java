package RandomSolutions.JavaFundamentals.Exam2019August03Group1;

import java.util.Scanner;

public class Pr01StringManipulator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String string = scan.nextLine();

        String command;
        while (!"End".equals(command = scan.nextLine())) {
            String[] tokens = command.split("\\s+");

            String output;
            switch (tokens[0]) {
            case "Translate":
                string = string.replaceAll(tokens[1], tokens[2]);
                output = string;
                break;
            case "Includes":
                output = string.contains(tokens[1]) ? "True" : "False";
                break;
            case "Start":
                output = string.startsWith(tokens[1]) ? "True" : "False";
                break;
            case "Lowercase":
                string = string.toLowerCase();
                output = string;
                break;
            case "FindIndex":
                output = Integer.toString(string.lastIndexOf(tokens[1].charAt(0)));
                break;
            case "Remove":
                int startIndex = Integer.parseInt(tokens[1]);
                int endIndex = startIndex + Integer.parseInt(tokens[2]);
                string = new StringBuilder(string)
                        .delete(startIndex, endIndex)
                        .toString();
                output = string;
                break;
            default:
                throw new IllegalArgumentException("Invalid command:" + tokens[0]);
            }

            System.out.println(output);
        }
    }
}
