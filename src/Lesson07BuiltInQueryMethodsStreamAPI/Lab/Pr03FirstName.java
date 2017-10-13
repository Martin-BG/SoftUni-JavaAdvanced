package Lesson07BuiltInQueryMethodsStreamAPI.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pr03FirstName {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Stream<String> names = Arrays.stream(reader.readLine().split("\\s+"));
        List<Character> chars = Arrays.stream(reader.readLine().split("\\s+"))
                .map(s -> s.toLowerCase().charAt(0))
                .distinct()
                .collect(Collectors.toList());

        Predicate<String> findMatches = s -> chars.contains(s.toLowerCase().charAt(0));

        Optional<String> match = names.filter(findMatches).sorted().findFirst();

        System.out.println(match.orElse("No match"));
    }
}