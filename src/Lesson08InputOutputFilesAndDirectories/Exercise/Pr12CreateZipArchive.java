package Lesson08InputOutputFilesAndDirectories.Exercise;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Pr12CreateZipArchive {

    public static void main(String[] args) {
        final String rootFolder = "src\\Lesson08InputOutputFilesAndDirectories\\Resources\\Files-and-Streams";
        final String zipFile = "src\\Lesson08InputOutputFilesAndDirectories\\Resources\\test.zip";

        zipFiles(rootFolder, zipFile, getFilesList(rootFolder));
    }

    private static void zipFiles(String rootFolder, String zipFile, List<String> filesList) {
        try (OutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            byte[] buffer = new byte[4096];

            for (String file : filesList) {
                try (FileInputStream in = new FileInputStream(rootFolder + File.separator + file)) {
                    zos.putNextEntry(new ZipEntry(file));
                    int len;
                    while ((len = in.read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> getFilesList(String rootFolder) {
        File root = new File(rootFolder);
        int rootPathLen = root.getAbsoluteFile().toString().length();

        List<String> filesList = new ArrayList<>();
        Deque<File> folders = new ArrayDeque<>();
        folders.offer(root);

        while (!folders.isEmpty()) {
            File current = folders.poll();
            File[] files = current.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        folders.offer(file);
                    } else {
                        filesList.add(file.getAbsoluteFile().toString().substring(rootPathLen + 1));
                    }
                }
            }
        }

        return filesList;
    }
}