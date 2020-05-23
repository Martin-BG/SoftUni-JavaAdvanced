package RandomSolutions.JavaFundamentals.L07MapsLambdaAndStreamApi.Exercise;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Pr04Orders {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        Map<String, List<Double>> orders = new LinkedHashMap<>();

        String input;
        while (!"buy".equals(input = scanner.nextLine())) {
            String[] tokens = input.split("\\s+");
            String name = tokens[0];
            double price = Double.parseDouble(tokens[1]);
            double quantity = Double.parseDouble(tokens[2]);

            orders.merge(name, List.of(price, quantity),
                    (oldList, newList) -> List.of(newList.get(0), oldList.get(1) + newList.get(1)));
        }

        orders.forEach((name, data) -> System.out.printf("%s -> %.2f%n", name, data.get(0) * data.get(1)));
    }
}
