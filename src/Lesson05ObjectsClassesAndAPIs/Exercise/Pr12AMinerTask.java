package Lesson05ObjectsClassesAndAPIs.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Pr12AMinerTask {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> quantities = new LinkedHashMap<>();

        String resource = reader.readLine();
        while (!"stop".equals(resource)) {
            int quantity = Integer.parseInt(reader.readLine());
            quantities.compute(resource, (k, v) -> (v == null) ? quantity : (v + quantity));
            resource = reader.readLine();
        }

        quantities.forEach((k, v) -> System.out.printf("%s -> %d%n", k, v));
    }
}