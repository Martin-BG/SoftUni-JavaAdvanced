package Lesson03StringProcessing.Lab;

import java.util.Scanner;

public class Pr01StudentsResults {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] studentTokens = scanner.nextLine()
                .replaceAll(" ", "")
                .split("[-,]");

        String name = studentTokens[0];
        double javaAdv = Double.parseDouble(studentTokens[1]);
        double javaOop = Double.parseDouble(studentTokens[2]);
        double advOop = Double.parseDouble(studentTokens[3]);

        double averageScore = (javaAdv + javaOop + advOop) / 3d;

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s|%7s|%7s|%7s|%7s|%n",
                "Name", "JAdv", "JavaOOP", "AdvOOP", "Average"));

        sb.append(String.format("%-10s|%7.2f|%7.2f|%7.2f|%7.4f|%n",
                name, javaAdv, javaOop, advOop, averageScore));

        System.out.print(sb);
    }
}