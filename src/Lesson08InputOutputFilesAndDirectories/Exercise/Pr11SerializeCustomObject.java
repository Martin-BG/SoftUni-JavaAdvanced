package Lesson08InputOutputFilesAndDirectories.Exercise;

import java.io.*;

public class Pr11SerializeCustomObject {

    public static void main(String[] args) {
        final String saveFile = "src\\Lesson08InputOutputFilesAndDirectories\\Resources\\course.ser";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile))) {
            Course course = new Course("Java Advanced", 99);
            System.out.println(course);
            oos.writeObject(course);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile))) {
            Course newCourse = (Course) ois.readObject();
            System.out.println(newCourse);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private static class Course implements Serializable {
        private String name;
        private Integer students;

        Course(String name, Integer students) {
            this.name = name;
            this.students = students;
        }

        @Override
        public String toString() {
            return "Course{" +
                    "name='" + name + '\'' +
                    ", students=" + students +
                    '}';
        }
    }
}