package Lesson08InputOutputFilesAndDirectories.Lab;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Pr04ExtractIntegers {

    public static void main(String[] args) {
        final String inFile = "src\\Lesson08InputOutputFilesAndDirectories\\Resources\\input.txt";
        final String outFile = "src\\Lesson08InputOutputFilesAndDirectories\\Resources\\output.txt";

        try (Scanner scanner = new Scanner(new FileInputStream(inFile));
             PrintWriter out = new PrintWriter(new FileOutputStream(outFile))) {
            while (scanner.hasNext()) {
                if (scanner.hasNextLong()) {
                    out.println(scanner.nextInt());
                }
                scanner.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}