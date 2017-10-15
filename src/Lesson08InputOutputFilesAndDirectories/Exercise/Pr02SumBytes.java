package Lesson08InputOutputFilesAndDirectories.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Pr02SumBytes {

    public static void main(String[] args) {
        final Path inPath = Paths.get("src\\Lesson08InputOutputFilesAndDirectories\\Resources\\input.txt");
        long sum = 0L;
        long characters = 0L;

        try (BufferedReader reader = Files.newBufferedReader(inPath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                char[] chars = line.toCharArray();
                characters += chars.length;
                for (char c : chars) {
                    sum += c;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(sum);
        System.out.println(characters);
    }
}