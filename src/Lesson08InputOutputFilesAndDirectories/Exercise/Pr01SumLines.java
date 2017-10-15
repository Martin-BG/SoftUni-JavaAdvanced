package Lesson08InputOutputFilesAndDirectories.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Pr01SumLines {

    public static void main(String[] args) {
        final Path inPath = Paths.get("src\\Lesson08InputOutputFilesAndDirectories\\Resources\\input.txt");

        try (BufferedReader reader = Files.newBufferedReader(inPath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                long sum = 0L;
                char[] chars = line.toCharArray();
                for (char c : chars) {
                    sum += c;
                }
                System.out.println(sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}