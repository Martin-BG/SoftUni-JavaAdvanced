package Lesson05ObjectsClassesAndAPIs.Lab;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Pr06AcademyGraduation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, double[]> students = new TreeMap<>();
        int studentsToRead = Integer.parseInt(scanner.nextLine());

        while (studentsToRead-- > 0) {
            String name = scanner.nextLine();
            double[] scores = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToDouble(Double::parseDouble)
                    .toArray();
            students.put(name, scores);
        }

        for (String key : students.keySet()) {
            System.out.printf("%s is graduated with %s%n", key, getAvg(students.get(key)));
        }
    }

    private static double getAvg(double[] scores) {
        double avg = 0d;
        for (double score : scores) {
            avg += score;
        }
        avg /= scores.length;
        return avg;
    }
}