package Lesson08InputOutputFilesAndDirectories.Lab;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Pr01ReadFile {

    public static void main(String[] args) {
        final String dataFile = "src\\Lesson08InputOutputFilesAndDirectories\\Resources\\input.txt";

        try (InputStream fileStream = new FileInputStream(dataFile)) {
            int oneByte = fileStream.read();
            while (oneByte >= 0) {
                System.out.printf("%s ", Integer.toBinaryString(oneByte));
                oneByte = fileStream.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}