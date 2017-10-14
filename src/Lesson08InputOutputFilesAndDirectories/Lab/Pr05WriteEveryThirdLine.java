package Lesson08InputOutputFilesAndDirectories.Lab;

import java.io.*;

public class Pr05WriteEveryThirdLine {

    public static void main(String[] args) {
        final String inFile = "src\\Lesson08InputOutputFilesAndDirectories\\Resources\\input.txt";
        final String outFile = "src\\Lesson08InputOutputFilesAndDirectories\\Resources\\output.txt";

        try (BufferedReader in = new BufferedReader(new FileReader(inFile));
             PrintWriter out = new PrintWriter(new FileWriter(outFile))) {
            int lineCounter = 0;
            String line;
            while ((line = in.readLine()) != null) {
                if (++lineCounter % 3 == 0) {
                    out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}