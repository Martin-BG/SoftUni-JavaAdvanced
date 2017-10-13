package Lesson07BuiltInQueryMethodsStreamAPI.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.OptionalDouble;

public class Pr04AverageOfDoubles {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        OptionalDouble average = Arrays.stream(reader.readLine().split("\\s+"))
                .filter(n -> !n.isEmpty())
                .mapToDouble(Double::parseDouble)
                .average();

        System.out.println(average.isPresent() ? String.format("%.2f", average.getAsDouble()) : "No match");
    }
}