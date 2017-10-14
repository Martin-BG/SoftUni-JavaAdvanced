package Lesson08InputOutputFilesAndDirectories.Lab;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Pr02WriteToAFile {

    public static void main(String[] args) {
        final String inFile = "src\\Lesson08InputOutputFilesAndDirectories\\Resources\\input.txt";
        final String outFile = "src\\Lesson08InputOutputFilesAndDirectories\\Resources\\output.txt";

        final Set<Character> punctuation = new HashSet<>(Arrays.asList(',', '.', '!', '?'));

        try (InputStream inputStream = new FileInputStream(inFile);
             OutputStream outputStream = new FileOutputStream(outFile)) {
            int oneByte = inputStream.read();
            while (oneByte >= 0) {
                if (!punctuation.contains((char) oneByte)) {
                    outputStream.write(oneByte);
                }
                oneByte = inputStream.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}