package Lesson05ObjectsClassesAndAPIs.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Pr06SimpleTextEditor {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder text = new StringBuilder();
        StringBuilder result = new StringBuilder();

        Deque<String> textHistory = new ArrayDeque<>();

        int commands = Integer.parseInt(reader.readLine());

        while (commands-- > 0) {
            String[] tokens = reader.readLine().split("\\s+");
            int command = Integer.parseInt(tokens[0]);

            switch (command) {
            case 1:
                textHistory.push(text.toString());
                text.append(tokens[1]);
                break;
            case 2:
                int newLength = text.length() - Integer.parseInt(tokens[1]);
                if (newLength < 0) {
                    newLength = 0;
                }
                textHistory.push(text.toString());
                text.setLength(newLength);
                break;
            case 3:
                int index = Integer.parseInt(tokens[1]);
                result.append(text.charAt(index - 1)).append(System.lineSeparator());
                break;
            case 4:
                text = new StringBuilder(textHistory.pop());
                break;
            default:
                break;
            }
        }

        System.out.println(result);
    }
}
