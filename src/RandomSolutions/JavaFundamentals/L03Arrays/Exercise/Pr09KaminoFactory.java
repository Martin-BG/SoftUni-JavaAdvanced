package RandomSolutions.JavaFundamentals.L03Arrays.Exercise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Pr09KaminoFactory {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        int[][] dnas = reader.lines()
                .skip(1L)
                .takeWhile(line -> !"Clone them!".equals(line))
                .map(line -> Arrays
                        .stream(line.split("!+"))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .toArray(int[][]::new);

        int bestDnaStart = 0;
        int bestDnaLength = 0;
        int bestDnaSum = 0;
        int bestDnaSample = 0;

        for (int sample = 0; sample < dnas.length; sample++) {
            int[] dna = dnas[sample];
            int currentDnaBestStart = 0;
            int currentDnaBestLength = 0;
            int currentDnaSum = 0;
            int currentStart = 0;
            int currentLength = 0;

            for (int i = 0; i < dna.length; i++) {
                if (dna[i] == 1) {
                    currentLength++;
                    currentDnaSum++;
                } else {
                    currentStart = i + 1;
                    currentLength = 0;
                }

                if (currentLength > currentDnaBestLength) {
                    currentDnaBestLength = currentLength;
                    currentDnaBestStart = currentStart;
                }
            }

            if (currentDnaBestLength > bestDnaLength ||
                    currentDnaBestLength == bestDnaLength &&
                            currentDnaBestStart < bestDnaStart ||
                    currentDnaBestLength == bestDnaLength &&
                            currentDnaBestStart == bestDnaStart &&
                            currentDnaSum > bestDnaSum) {
                bestDnaStart = currentDnaBestStart;
                bestDnaLength = currentDnaBestLength;
                bestDnaSum = currentDnaSum;
                bestDnaSample = sample;
            }
        }

        System.out.printf("Best DNA sample %d with sum: %d.%n%s",
                bestDnaSample + 1,
                bestDnaSum,
                Arrays.toString(dnas[bestDnaSample])
                        .replaceAll("[\\[\\],]", "")
        );
    }
}
