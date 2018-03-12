package Lesson08InputOutputFilesAndDirectories.Exercise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Pr07MergeTwoFiles {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Input file One: ");
            final Path inputPathOne = Paths.get(reader.readLine().trim());

            System.out.print("Input file Two: ");
            final Path inputPathTwo = Paths.get(reader.readLine().trim());

            System.out.print("Output file: ");
            final Path outputPath = Paths.get(reader.readLine().trim());

            try (BufferedReader fileReaderOne = Files.newBufferedReader(inputPathOne);
                 BufferedReader fileReaderTwo = Files.newBufferedReader(inputPathTwo);
                 BufferedWriter fileWriter = Files.newBufferedWriter(outputPath)) {

                fileReaderOne.lines().forEach(line -> {
                    try {
                        fileWriter.write(line);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                fileReaderTwo.lines().forEach(line -> {
                    try {
                        fileWriter.write(line);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
