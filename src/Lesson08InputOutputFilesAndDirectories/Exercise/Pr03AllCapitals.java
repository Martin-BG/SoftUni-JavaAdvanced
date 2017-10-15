package Lesson08InputOutputFilesAndDirectories.Exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Pr03AllCapitals {

    public static void main(String[] args) {
        final Path inPath = Paths.get("src\\Lesson08InputOutputFilesAndDirectories\\Resources\\input.txt");
        final Path outPath = Paths.get("src\\Lesson08InputOutputFilesAndDirectories\\Resources\\output.txt");

        try (BufferedReader br = Files.newBufferedReader(inPath);
             BufferedWriter bw = Files.newBufferedWriter(outPath)) {
            br.lines()
                    .map(String::toUpperCase)
                    .forEach(str -> {
                        try {
                            bw.write(str);
                            bw.newLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}