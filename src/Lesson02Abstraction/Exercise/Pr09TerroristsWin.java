package Lesson02Abstraction.Exercise;

import java.util.Scanner;

public class Pr09TerroristsWin {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        char[] playGround = scanner.nextLine().toCharArray();

        for (int index = 0; index < playGround.length; index++) {
            if (playGround[index] == '|') {
                int startIndex = index;
                int endIndex = index;
                int sum = 0;

                while (++index < playGround.length) {
                    if (playGround[index] == '|') {
                        endIndex = index + 1;
                        break;
                    }
                    sum += playGround[index];
                }

                int bombPower = sum % 10;

                startIndex -= bombPower;
                endIndex += bombPower;

                if (startIndex < 0) {
                    startIndex = 0;
                }

                if (endIndex > playGround.length) {
                    endIndex = playGround.length;
                }

                for (int i = startIndex; i < endIndex; i++) {
                    playGround[i] = '.';
                }
            }
        }

        System.out.println(playGround);
    }
}
