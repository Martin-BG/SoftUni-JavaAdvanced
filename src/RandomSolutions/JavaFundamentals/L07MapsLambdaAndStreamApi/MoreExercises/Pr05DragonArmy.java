package RandomSolutions.JavaFundamentals.L07MapsLambdaAndStreamApi.MoreExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Pr05DragonArmy {
    private static final Pattern DRAGON_PATTERN =
            Pattern.compile("^(?<type>.+)\\s+(?<name>.+)\\s+(?<damage>.+)\\s+(?<health>.+)\\s+(?<armor>.+)$");

    public static void main(String[] args) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        reader.lines()
                .limit(Long.parseLong(reader.readLine()))
                .sequential()
                .map(DRAGON_PATTERN::matcher)
                .filter(Matcher::matches)
                .map(matcherToDragon())
                .collect(dragonsCollector())
                .forEach(dragonsGroupConsolePrinter());
    }

    private static Function<Matcher, Dragon> matcherToDragon() {
        final String NULL_STR = "null";
        return matcher -> {
            final String type = matcher.group("type");
            final String name = matcher.group("name");
            final String damageStr = matcher.group("damage");
            final String healthStr = matcher.group("health");
            final String armorStr = matcher.group("armor");
            final int damage = NULL_STR.equals(damageStr) ? 45 : Integer.parseInt(damageStr);
            final int health = NULL_STR.equals(healthStr) ? 250 : Integer.parseInt(healthStr);
            final int armor = NULL_STR.equals(armorStr) ? 10 : Integer.parseInt(armorStr);
            return new Dragon(type, name, damage, health, armor);
        };
    }

    private static Collector<Dragon, ?, LinkedHashMap<String, TreeMap<String, Dragon>>> dragonsCollector() {
        return Collectors.groupingBy(
                dragon -> dragon.type,
                LinkedHashMap::new,
                Collectors.collectingAndThen(Collectors.toList(),
                        dragonsList -> dragonsList.stream()
                                .sequential()
                                .collect(Collectors.toMap(dragon -> dragon.name,
                                        Function.identity(),
                                        (existing, replacement) -> replacement,
                                        TreeMap::new)
                                )
                )
        );
    }

    private static BiConsumer<String, TreeMap<String, Dragon>> dragonsGroupConsolePrinter() {
        return (type, dragons) -> {
            final double averageDamage = dragons.values().stream().mapToDouble(d -> d.damage).average().orElse(0.0);
            final double averageHealth = dragons.values().stream().mapToDouble(d -> d.health).average().orElse(0.0);
            final double averageArmor = dragons.values().stream().mapToDouble(d -> d.armor).average().orElse(0.0);
            System.out.printf("%s::(%.2f/%.2f/%.2f)%n", type, averageDamage, averageHealth, averageArmor);
            dragons.values().forEach(dragon ->
                    System.out.printf("-%s -> damage: %d, health: %d, armor: %d%n",
                            dragon.name, dragon.damage, dragon.health, dragon.armor));
        };
    }

    private static final class Dragon {
        private final String type;
        private final String name;
        private final int damage;
        private final int health;
        private final int armor;

        public Dragon(String type, String name, int damage, int health, int armor) {
            this.type = type;
            this.name = name;
            this.damage = damage;
            this.health = health;
            this.armor = armor;
        }
    }
}

