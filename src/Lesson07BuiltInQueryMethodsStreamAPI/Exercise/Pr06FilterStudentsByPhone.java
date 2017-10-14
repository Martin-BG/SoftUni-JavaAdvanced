package Lesson07BuiltInQueryMethodsStreamAPI.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Pr06FilterStudentsByPhone {

    public static void main(String[] args) {
        final String dataFile = "src\\Lesson07BuiltInQueryMethodsStreamAPI\\Resources\\StudentData.txt";

        try (BufferedReader br = Files.newBufferedReader(Paths.get(dataFile))) {
            br.lines()
                    .skip(1)
                    .map(line -> line.split("\\s+"))
                    .filter(x -> x[10].startsWith("02") || x[10].startsWith("+3592"))
                    .map(x -> x[1] + " " + x[2] + " " + x[10])
                    .forEachOrdered(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}