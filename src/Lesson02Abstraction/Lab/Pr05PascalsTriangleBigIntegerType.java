package Lesson02Abstraction.Lab;

import java.math.BigInteger;
import java.util.Scanner;

public class Pr05PascalsTriangleBigIntegerType {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lines = Integer.parseInt(scanner.nextLine());

        BigInteger[][] triangle = new BigInteger[lines][];

        for (int line = 0; line < lines; line++) {
            int elements = line + 1;

            triangle[line] = new BigInteger[elements];
            triangle[line][0] = BigInteger.ONE;
            triangle[line][elements - 1] = BigInteger.ONE;

            if (line > 1) {
                for (int col = 1; col < elements - 1; col++) {
                    triangle[line][col] = triangle[line - 1][col - 1].add(triangle[line - 1][col]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < lines; row++) {
            for (int col = 0; col < triangle[row].length; col++) {
                if (col > 0) {
                    sb.append(" ");
                }

                sb.append(triangle[row][col]);
            }

            sb.append(System.lineSeparator());
        }

        System.out.println(sb.toString());
    }
}
