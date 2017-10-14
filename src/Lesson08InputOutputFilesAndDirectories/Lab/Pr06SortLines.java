package Lesson08InputOutputFilesAndDirectories.Lab;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Pr06SortLines {

    public static void main(String[] args) {
        final Path inPath = Paths.get("src\\Lesson08InputOutputFilesAndDirectories\\Resources\\input.txt");
        final Path outPath = Paths.get("src\\Lesson08InputOutputFilesAndDirectories\\Resources\\output.txt");

        try {
            List<String> lines = Files.readAllLines(inPath);
            Collections.sort(lines);
            Files.write(outPath, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}