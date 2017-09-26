package Lesson03StringProcessing.Lab;

import java.util.Scanner;

public class Pr02ParseURL {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String urlAddress = scanner.nextLine();

        String[] parts = urlAddress.split("://");

        if (parts.length != 2) {
            System.out.println("Invalid URL");
        } else {
            String protocol = parts[0];
            String server = parts[1].substring(0, parts[1].indexOf('/'));
            String resources = parts[1].substring(parts[1].indexOf('/') + 1);

            System.out.printf("Protocol = %s%nServer = %s%nResources = %s%n",
                    protocol, server, resources);
        }
    }
}
