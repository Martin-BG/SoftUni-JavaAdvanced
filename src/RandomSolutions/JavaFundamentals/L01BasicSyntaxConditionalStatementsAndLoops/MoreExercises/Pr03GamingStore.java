package RandomSolutions.JavaFundamentals.L01BasicSyntaxConditionalStatementsAndLoops.MoreExercises;

import java.util.*;

public class Pr03GamingStore {
    public static void main(String[] args) {
        final GameShop gameShop = new GameShop();
        gameShop.addGames(List.of(
                new Game("OutFall 4", 39.99),
                new Game("CS: OG", 15.99),
                new Game("Zplinter Zell", 19.99),
                new Game("Honored 2", 59.99),
                new Game("RoverWatch", 29.99),
                new Game("RoverWatch Origins Edition", 39.99)
        ));

        Scanner scanner = new Scanner(System.in);

        double money = Double.parseDouble(scanner.nextLine());
        final Customer customer = new Customer(money);

        String gameName;
        while (customer.hasMoney() &&
                !"Game Time".equals(gameName = scanner.nextLine())) {
            System.out.println(gameShop
                    .findGame(gameName)
                    .map(game -> customer.tryBuyGame(game) ?
                            "Bought " + game.getName() :
                            "Too Expensive")
                    .orElse("Not Found"));
        }

        if (!customer.hasMoney()) {
            System.out.println("Out of money!");
        } else {
            System.out.printf("Total spent: $%.2f. Remaining: $%.2f",
                    customer.getMoneySpent(), customer.getMoney());
        }
    }

    private static final class Game {
        private final String name;
        private final double price;

        public Game(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return this.name;
        }

        public double getPrice() {
            return this.price;
        }
    }

    private static final class GameShop {
        private final Map<String, Game> games;

        public GameShop() {
            games = new HashMap<>();
        }

        public void addGames(Collection<Game> games) {
            games.forEach(game -> this.games.put(game.getName(), game));
        }

        public Optional<Game> findGame(String name) {
            return Optional.ofNullable(games.get(name));
        }
    }

    private static final class Customer {
        private double money;
        private double moneySpent;

        public Customer(double money) {
            this.money = money;
            moneySpent = 0;
        }

        public boolean tryBuyGame(Game game) {
            if (Double.compare(money, game.getPrice()) < 0) {
                return false;
            }
            money -= game.getPrice();
            moneySpent += game.getPrice();
            return true;
        }

        public boolean hasMoney() {
            return money > 0.0;
        }

        public double getMoney() {
            return money;
        }

        public double getMoneySpent() {
            return moneySpent;
        }
    }
}
