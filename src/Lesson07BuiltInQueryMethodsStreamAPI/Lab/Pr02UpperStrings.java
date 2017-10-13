package Lesson07BuiltInQueryMethodsStreamAPI.Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr02UpperStrings {

    public static void main(String[] args) throws IOException {
        Arrays.stream((new BufferedReader(new InputStreamReader(System.in)).readLine().split("\\s+")))
                .map(String::toUpperCase)
                .forEach(n -> System.out.print(n + " "));
    }
}