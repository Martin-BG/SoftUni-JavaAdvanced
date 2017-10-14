package Lesson08InputOutputFilesAndDirectories.Lab;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;

public class Pr08NestedFolders {

    public static void main(String[] args) {
        final String path = "src\\Lesson08InputOutputFilesAndDirectories\\Resources\\Files-and-Streams";
        File root = new File(path);

        Deque<File> folders = new ArrayDeque<>();
        folders.offer(root);

        int foldersCount = 0;

        while (!folders.isEmpty()) {
            File current = folders.poll();
            System.out.println(current.getName());
            foldersCount++;

            File[] files = current.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        folders.offer(file);
                    }
                }
            }
        }

        System.out.printf("%d folders%n", foldersCount);
    }
}