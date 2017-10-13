package Lesson07BuiltInQueryMethodsStreamAPI.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Pr07MapDistricts {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, List<Integer>> cities = new HashMap<>();

        List<String> tokens = Arrays.asList(reader.readLine().split("\\s+"));
        for (String token : tokens) {
            String[] tokenArgs = token.split(":");
            String city = tokenArgs[0];

            int districtPopulation = Integer.parseInt(tokenArgs[1]);
            cities.putIfAbsent(city, new ArrayList<>());
            cities.get(city).add(districtPopulation);
        }

        int bound = Integer.parseInt(reader.readLine());

        cities.entrySet().stream()
                .filter(kv -> kv.getValue().stream()
                        .mapToLong(Long::valueOf)
                        .sum() >= bound)
                .sorted((kv1, kv2) -> Integer.compare(
                        kv2.getValue().stream().mapToInt(Integer::valueOf).sum(),
                        kv1.getValue().stream().mapToInt(Integer::valueOf).sum()))
                .forEach(kv -> {
                    System.out.printf("%s: ", kv.getKey());
                    kv.getValue().stream()
                            .sorted(Comparator.reverseOrder())
                            .limit(5)
                            .forEach(dp -> System.out.printf("%s ", dp));
                    System.out.println();
                });
    }
}