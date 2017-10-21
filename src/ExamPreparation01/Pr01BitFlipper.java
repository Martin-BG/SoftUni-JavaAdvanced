package ExamPreparation01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Pr01BitFlipper {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        BigInteger number = new BigInteger(reader.readLine());

        BigInteger mask = new BigInteger("7").shiftLeft(61);    //0b111

        while (mask.compareTo(new BigInteger("7")) >= 0) {
            BigInteger current = number.and(mask);

            if (current.compareTo(BigInteger.ZERO) == 0) {  //all 3 its are 0
                number = number.or(mask);
                mask = mask.shiftRight(2);
            } else if (current.compareTo(mask) == 0) {      //all 3 bits are 1
                number = number.xor(mask);
                mask = mask.shiftRight(2);
            }

            mask = mask.shiftRight(1);
        }

        System.out.println(number);
    }
}