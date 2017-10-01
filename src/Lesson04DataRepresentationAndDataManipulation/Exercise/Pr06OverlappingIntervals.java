package Lesson04DataRepresentationAndDataManipulation.Exercise;

import java.util.Arrays;
import java.util.Scanner;

public class Pr06OverlappingIntervals {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int intervalsCount = Integer.parseInt(scanner.nextLine());

        int[] start = new int[intervalsCount];
        int[] end = new int[intervalsCount];

        for (int i = 0; i < intervalsCount; i++) {
            int[] currentInterval = Arrays.stream(scanner.nextLine().split(","))
                    .mapToInt(Integer::parseInt).toArray();
            start[i] = currentInterval[0];
            end[i] = currentInterval[1];
        }
        // Algorithm: http://www.zrzahid.com/maximum-number-of-overlapping-intervals/
        Arrays.sort(start);
        Arrays.sort(end);

        int currentOverlap = 0;
        int i = 0;
        int j = 0;
        boolean overlappingIntervals = false;

        while (i < intervalsCount && j < intervalsCount) {
            if (start[i] <= end[j]) {
                currentOverlap++;
                i++;
            } else {
                currentOverlap--;
                j++;
            }

            if (currentOverlap > 1) {
                overlappingIntervals = true;
                break;
            }
        }

        System.out.println(overlappingIntervals);
    }
}
