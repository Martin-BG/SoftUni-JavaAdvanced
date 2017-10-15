package Lesson08InputOutputFilesAndDirectories.Exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Pr05LineNumbers {

    public static void main(String[] args) {
        final Path inPath = Paths.get("src\\Lesson08InputOutputFilesAndDirectories\\Resources\\input.txt");
        final Path outPath = Paths.get("src\\Lesson08InputOutputFilesAndDirectories\\Resources\\output.txt");

        final int[] lines = {1};
        try (BufferedReader br = Files.newBufferedReader(inPath);
             BufferedWriter bw = Files.newBufferedWriter(outPath)) {
            br.lines()
                    .forEach(str -> {
                        try {
                            bw.write(String.format("%d. %s%n", lines[0]++, str));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}