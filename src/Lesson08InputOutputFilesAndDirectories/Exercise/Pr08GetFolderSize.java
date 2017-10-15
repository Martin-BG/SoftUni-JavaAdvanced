package Lesson08InputOutputFilesAndDirectories.Exercise;

import java.io.File;

public class Pr08GetFolderSize {

    public static void main(String[] args) {
        final String inPath = "src\\Lesson08InputOutputFilesAndDirectories\\Resources\\Files-and-Streams";

        long folderSize = 0L;
        File file = new File(inPath);
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (!f.isDirectory()) {
                        folderSize += f.length();
                    }
                }
            }
        }

        System.out.printf("Folder size:: %d%n", folderSize);
    }
}