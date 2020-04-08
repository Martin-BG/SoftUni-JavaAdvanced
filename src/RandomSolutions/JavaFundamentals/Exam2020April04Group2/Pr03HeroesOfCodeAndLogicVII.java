package RandomSolutions.JavaFundamentals.Exam2020April04Group2;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Pr03HeroesOfCodeAndLogicVII {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int heroesCount = Integer.parseInt(scanner.nextLine());

        Map<String, Hero> heroes = parseHeroes(scanner, heroesCount);

        processHeroActions(scanner, heroes);

        System.out.println(heroesInfo(heroes.values()));
    }

    private static Map<String, Hero> parseHeroes(Scanner scanner, int heroesCount) {
        return IntStream.range(0, heroesCount)
                .mapToObj(i -> {
                    String[] tokens = scanner.nextLine().trim().split("\\s+");
                    String heroName = tokens[0];
                    int hitPoints = Integer.parseInt(tokens[1]);
                    int manaPoints = Integer.parseInt(tokens[2]);
                    return new Hero(heroName, hitPoints, manaPoints);
                })
                .collect(Collectors.toMap(Hero::getName, Function.identity()));
    }

    private static void processHeroActions(Scanner scanner, Map<String, Hero> heroes) {
        String input;
        while (!"End".equals(input = scanner.nextLine())) {
            String[] tokens = input.split(" - ");
            String command = tokens[0];
            String heroName = tokens[1];
            String[] output = new String[1];

            switch (command) {
            case "CastSpell":
                int manaPointsNeeded = Integer.parseInt(tokens[2]);
                String spellName = tokens[3];
                heroes.computeIfPresent(heroName, castSpellBiFunction(output, manaPointsNeeded, spellName));
                break;
            case "TakeDamage":
                int damage = Integer.parseInt(tokens[2]);
                String attacker = tokens[3];
                heroes.computeIfPresent(heroName, damageHeroBiFunction(output, damage, attacker));
                break;
            case "Recharge":
                int manaPoints = Integer.parseInt(tokens[2]);
                heroes.computeIfPresent(heroName, rechargeHeroBiFunction(output, manaPoints));
                break;
            case "Heal":
                int hitPoints = Integer.parseInt(tokens[2]);
                heroes.computeIfPresent(heroName, healHeroBiFunction(output, hitPoints));
                break;
            default:
                throw new UnsupportedOperationException("Unknown command: " + command);
            }

            System.out.println(output[0]);
        }
    }

    private static String heroesInfo(Collection<Hero> heroes) {
        return heroes.stream()
                .sorted(Comparator
                        .comparingInt(Hero::getHitPoints).reversed()
                        .thenComparing(Hero::getName))
                .map(hero -> String.format(
                        "%s%n  HP: %d%n  MP: %d",
                        hero.getName(), hero.getHitPoints(), hero.getManaPoints()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private static BiFunction<String, Hero, Hero> castSpellBiFunction(String[] output, int manaPointsNeeded, String spellName) {
        return (name, hero) -> {
            if (hero.castSpell(manaPointsNeeded)) {
                output[0] = String.format(
                        "%s has successfully cast %s and now has %d MP!",
                        hero.getName(), spellName, hero.getManaPoints());
            } else {
                output[0] = String.format(
                        "%s does not have enough MP to cast %s!",
                        hero.getName(), spellName);
            }
            return hero;
        };
    }

    private static BiFunction<String, Hero, Hero> damageHeroBiFunction(String[] output, int damage, String attacker) {
        return (name, hero) -> {
            hero.takeDamage(damage);
            if (hero.isAlive()) {
                output[0] = String.format(
                        "%s was hit for %d HP by %s and now has %d HP left!",
                        hero.getName(), damage, attacker, hero.getHitPoints());
                return hero;
            } else {
                output[0] = String.format(
                        "%s has been killed by %s!",
                        hero.getName(), attacker);
                return null;
            }
        };
    }

    private static BiFunction<String, Hero, Hero> rechargeHeroBiFunction(String[] output, int amount) {
        return (name, hero) -> {
            int recovered = hero.rechargeHero(amount);
            output[0] = String.format(
                    "%s recharged for %d MP!",
                    hero.getName(), recovered);
            return hero;
        };
    }

    private static BiFunction<String, Hero, Hero> healHeroBiFunction(String[] output, int amount) {
        return (name, hero) -> {
            int recovered = hero.healHero(amount);
            output[0] = String.format(
                    "%s healed for %d HP!",
                    hero.getName(), recovered);
            return hero;
        };
    }

    private static class Hero {
        private static final int HIT_POINTS_MAX = 100;
        private static final int MANA_POINTS_MAX = 200;

        private final String name;
        private int hitPoints;
        private int manaPoints;

        public Hero(String name, int hitPoints, int manaPoints) {
            this.name = name;
            addHitPoints(hitPoints);
            addManaPoints(manaPoints);
        }

        private int addHitPoints(int hitPoints) {
            int restored = hitPoints;
            this.hitPoints += hitPoints;
            if (this.hitPoints > HIT_POINTS_MAX) {
                restored -= this.hitPoints - HIT_POINTS_MAX;
                this.hitPoints = HIT_POINTS_MAX;
            }
            return restored;
        }

        private int addManaPoints(int manaPoints) {
            int restored = manaPoints;
            this.manaPoints += manaPoints;
            if (this.manaPoints > MANA_POINTS_MAX) {
                restored -= this.manaPoints - MANA_POINTS_MAX;
                this.manaPoints = MANA_POINTS_MAX;
            }
            return restored;
        }

        public boolean castSpell(int manaPointsNeeded) {
            if (!canCastSpell(manaPointsNeeded)) {
                return false;
            }
            addManaPoints(-manaPointsNeeded);
            return true;
        }

        private boolean canCastSpell(int manaPointsNeeded) {
            return manaPoints >= manaPointsNeeded;
        }

        public void takeDamage(int damage) {
            addHitPoints(-damage);
        }

        public int rechargeHero(int amount) {
            return addManaPoints(amount);
        }

        public int healHero(int amount) {
            return addHitPoints(amount);
        }

        public boolean isAlive() {
            return hitPoints > 0;
        }

        public String getName() {
            return name;
        }

        public int getHitPoints() {
            return hitPoints;
        }

        public int getManaPoints() {
            return manaPoints;
        }
    }
}