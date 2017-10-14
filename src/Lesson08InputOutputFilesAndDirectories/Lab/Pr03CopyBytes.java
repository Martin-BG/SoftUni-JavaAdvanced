package Lesson08InputOutputFilesAndDirectories.Lab;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Pr03CopyBytes {
    public static void main(String[] args) {
        final String inFile = "src\\Lesson08InputOutputFilesAndDirectories\\Resources\\input.txt";
        final String outFile = "src\\Lesson08InputOutputFilesAndDirectories\\Resources\\output.txt";

        final Set<Character> special = new HashSet<>(Arrays.asList(' ', '\n'));

        try (InputStream inputStream = new FileInputStream(inFile);
             OutputStream outputStream = new FileOutputStream(outFile)) {
            int oneByte;
            while ((oneByte = inputStream.read()) >= 0) {
                if (!special.contains((char) oneByte)) {
                    char[] digits = String.valueOf(oneByte).toCharArray();
                    for (char digit : digits) {
                        outputStream.write(digit);
                    }
                } else {
                    outputStream.write(oneByte);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}