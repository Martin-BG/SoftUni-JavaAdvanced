package Lesson08InputOutputFilesAndDirectories.Exercise;

import java.io.*;

public class Pr09CopyAFile {

    public static void main(String[] args) {
        final String source = "src\\Lesson08InputOutputFilesAndDirectories\\Resources\\input.txt";
        final String destination = "src\\Lesson08InputOutputFilesAndDirectories\\Resources\\copy-of-input.txt";

        try (InputStream is = new FileInputStream(source);
             OutputStream os = new FileOutputStream(destination)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}