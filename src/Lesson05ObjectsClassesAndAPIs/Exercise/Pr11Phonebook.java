package Lesson05ObjectsClassesAndAPIs.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Pr11Phonebook {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, String> phonebook = new HashMap<>();

        String line = reader.readLine();
        while (!"search".equals(line)) {
            String[] entry = line.split("-");
            phonebook.put(entry[0], entry[1]);
            line = reader.readLine();
        }

        line = reader.readLine();
        while (!"stop".equals(line)) {
            String number = phonebook.getOrDefault(line, null);
            if (number != null) {
                System.out.printf("%s -> %s%n", line, number);
            } else {
                System.out.printf("Contact %s does not exist.%n", line);
            }
            line = reader.readLine();
        }
    }
}