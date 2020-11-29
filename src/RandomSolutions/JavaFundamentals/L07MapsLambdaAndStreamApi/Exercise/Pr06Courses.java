package RandomSolutions.JavaFundamentals.L07MapsLambdaAndStreamApi.Exercise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Pr06Courses {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        reader.lines()
                .takeWhile(line -> !"end".equals(line))
                .map(line -> line.split(" : "))
                .collect(Collectors.groupingBy(arr -> arr[0],
                        LinkedHashMap::new,
                        Collectors.mapping(arr -> arr[1], Collectors.toList())))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, List<String>>comparingByValue(Comparator.comparingInt(List::size))
                        .reversed())
                .forEach(kvp -> {
                    System.out.printf("%s: %d%n", kvp.getKey(), kvp.getValue().size());
                    kvp.getValue().stream()
                            .sorted()
                            .map(name -> "-- " + name)
                            .forEach(System.out::println);
                });
    }
}