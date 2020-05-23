package RandomSolutions.JavaFundamentals.L07MapsLambdaAndStreamApi.Exercise;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.stream.Collectors;

public class Pr04OrdersWithClass {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        reader.lines()
                .takeWhile(line -> !"buy".equals(line))
                .map(Order::fromString)
                .collect(Collectors.groupingBy(
                        Order::getProductName,
                        LinkedHashMap::new,
                        Collectors.reducing(Order::updateWith)))
                .values()
                .stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Order::info)
                .forEach(System.out::println);
    }

    private static final class Order {
        private final String productName;
        private double priceForOne;
        private int quantity;

        private Order(String productName, double priceForOne, int quantity) {
            this.productName = productName;
            this.priceForOne = priceForOne;
            this.quantity = quantity;
        }

        public static Order fromString(String orderData) {
            String[] tokens = orderData.split("\\s+");
            String productName = tokens[0];
            double priceForOne = Double.parseDouble(tokens[1]);
            int quantity = Integer.parseInt(tokens[2]);

            return new Order(productName, priceForOne, quantity);
        }

        public Order updateWith(Order other) {
            priceForOne = other.priceForOne;
            quantity += other.quantity;
            return this;
        }

        public String info() {
            return String.format("%s -> %.2f", productName, priceForOne * quantity);
        }

        public String getProductName() {
            return productName;
        }
    }
}
