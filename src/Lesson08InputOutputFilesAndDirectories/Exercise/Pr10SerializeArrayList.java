package Lesson08InputOutputFilesAndDirectories.Exercise;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pr10SerializeArrayList {

    public static void main(String[] args) {
        final String saveFile = "src\\Lesson08InputOutputFilesAndDirectories\\Resources\\ArrayList.obj";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile))) {
            List<Double> numbers = new ArrayList<>();
            Collections.addAll(numbers, 1d, 2d, 5d, 7.23d, 234.5d, -23d);
            System.out.println(numbers);
            oos.writeObject(numbers);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile))) {
            @SuppressWarnings("unchecked")
            List<Double> numbers = (List<Double>) ois.readObject();
            System.out.println(numbers);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}