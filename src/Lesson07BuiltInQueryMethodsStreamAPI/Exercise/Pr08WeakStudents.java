package Lesson07BuiltInQueryMethodsStreamAPI.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class Pr08WeakStudents {

    public static void main(String[] args) {
        final String dataFile = "src\\Lesson07BuiltInQueryMethodsStreamAPI\\Resources\\StudentData.txt";

        try (BufferedReader br = Files.newBufferedReader(Paths.get(dataFile))) {
            br.lines()
                    .skip(1)
                    .map(line -> line.split("\\s+"))
                    .filter(x -> Stream.of(x[6], x[7], x[8], x[9])
                            .filter(n -> "2".equals(n) || "3".equals(n))
                            .count() >= 2
                    )
                    .sorted(Comparator.comparing(
                            x -> Stream.of(x[6], x[7], x[8], x[9])
                                    .mapToInt(Integer::parseInt)
                                    .sum()
                            )
                    )
                    .forEachOrdered(x -> System.out
                            .printf("%s %s %s%n",
                                    x[1], x[2],
                                    Stream.of(x[6], x[7], x[8], x[9]).sorted().collect(joining(" "))
                            )
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}