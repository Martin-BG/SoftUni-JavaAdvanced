package Lesson08InputOutputFilesAndDirectories.Lab;

import java.io.*;

public class Pr09SerializeCustomObject {

    public static void main(String[] args) {
        final String saveFile = "src\\Lesson08InputOutputFilesAndDirectories\\Resources\\save.txt";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile))) {
            Cube cube = new Cube("green", 15.3d, 12.4d, 3d);
            System.out.println(cube);
            oos.writeObject(cube);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile))) {
            Cube newCube = (Cube) ois.readObject();
            System.out.println(newCube);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private static class Cube implements Serializable {
        private String color;
        private Double width;
        private Double height;
        private Double depth;

        Cube(String color, Double width, Double height, Double depth) {
            this.color = color;
            this.width = width;
            this.height = height;
            this.depth = depth;
        }

        @Override
        public String toString() {
            return "Cube{" +
                    "color='" + color + '\'' +
                    ", width=" + width +
                    ", height=" + height +
                    ", depth=" + depth +
                    '}';
        }
    }
}