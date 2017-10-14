package Lesson07BuiltInQueryMethodsStreamAPI.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Pr09StudentsByEnrollmentYear {

    public static void main(String[] args) {
        final String dataFile = "src\\Lesson07BuiltInQueryMethodsStreamAPI\\Resources\\StudentData.txt";

        try (BufferedReader br = Files.newBufferedReader(Paths.get(dataFile))) {
            br.lines()
                    .skip(1)
                    .map(line -> line.split("\\s+"))
                    .collect(Collectors
                            .groupingBy(
                                    x -> "20" + x[0].substring(4) + ":",
                                    Collectors.mapping(x -> "-- " + x[1] + " " + x[2],
                                            Collectors.toCollection(TreeSet::new)
                                    )
                            )
                    )
                    .entrySet()
                    .stream()
                    .sorted(Comparator.comparing(Map.Entry::getKey))
                    .forEachOrdered(entry -> {
                        System.out.println(entry.getKey());
                        entry.getValue().forEach(System.out::println);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}