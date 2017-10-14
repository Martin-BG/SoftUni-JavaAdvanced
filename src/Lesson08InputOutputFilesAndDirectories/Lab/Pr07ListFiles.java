package Lesson08InputOutputFilesAndDirectories.Lab;

import java.io.File;

public class Pr07ListFiles {

    public static void main(String[] args) {
        final String inPath = "src\\Lesson08InputOutputFilesAndDirectories\\Resources\\Files-and-Streams";

        File file = new File(inPath);
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (!f.isDirectory()) {
                        System.out.printf("%s: %d%n", f.getName(), f.length());
                    }
                }
            }
        }
    }
}