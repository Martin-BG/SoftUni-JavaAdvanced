package Lesson05ObjectsClassesAndAPIs.Lab;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Pr05SoftUniParty {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<String> guests = new TreeSet<>();
        Set<String> vipGuests = new TreeSet<>();

        String line = scanner.nextLine();
        while (!"PARTY".equals(line)) {
            if (Character.isDigit(line.charAt(0))) {
                vipGuests.add(line);
            } else {
                guests.add(line);
            }

            line = scanner.nextLine();
        }
        line = scanner.nextLine();
        while (!"END".equals(line)) {
            if (Character.isDigit(line.charAt(0))) {
                vipGuests.remove(line);
            } else {
                guests.remove(line);
            }

            line = scanner.nextLine();
        }

        System.out.println(vipGuests.size() + guests.size());
        for (String guest : vipGuests) {
            System.out.println(guest);
        }
        for (String guest : guests) {
            System.out.println(guest);
        }
    }
}