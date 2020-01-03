package Lesson07BuiltInQueryMethodsStreamAPI.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Pr09StudentsByEnrollmentYearSimplified {

    public static void main(String[] args) {
        final String dataFile = "src\\Lesson07BuiltInQueryMethodsStreamAPI\\Resources\\StudentData.txt";

        try (BufferedReader br = Files.newBufferedReader(Paths.get(dataFile))) {
            br.lines()
                    .skip(1)
                    .map(line -> line.split("\\s+"))
                    .collect(
                            Collectors.groupingBy(
                                    tokens -> "20" + tokens[0].substring(4) + ":",
                                    TreeMap::new,
                                    Collectors.mapping(
                                            tokens -> "-- " + tokens[1] + " " + tokens[2],
                                            Collectors.toCollection(TreeSet::new)
                                    )
                            )
                    )
                    .forEach((enrollmentYear, students) -> {
                        System.out.println(enrollmentYear);
                        students.forEach(System.out::println);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}