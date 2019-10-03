package ExamPractice.Exam2019June23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Pr01SpaceshipCrafting {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        ArrayDeque<Integer> liquids = Arrays
                .stream(reader.readLine().trim().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        List<Integer> physicals = Arrays
                .stream(reader.readLine().trim().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(LinkedList::new));

        Map<Material, Integer> materials = Arrays.stream(Material.values())
                .sorted((m1, m2) -> m1.name().compareTo(m2.name))
                .collect(Collectors.toMap(m -> m, m -> 0, (a, b) -> a, LinkedHashMap::new));

        while (!liquids.isEmpty() && !physicals.isEmpty()) {
            int liquid = liquids.pollFirst();
            int physical = physicals.remove(physicals.size() - 1);
            int value = liquid + physical;

            Material material = Material.fromValue(value);
            if (material != null) {
                materials.computeIfPresent(material, (k, v) -> ++v);
            } else {
                physicals.add(physical + 3);
            }
        }

        final StringBuilder sb = new StringBuilder();

        boolean canBuild = materials.values().stream().filter(v -> v > 0).count() == materials.size();
        if (canBuild) {
            sb.append("Wohoo! You succeeded in building the spaceship!");
        } else {
            sb.append("Ugh, what a pity! You didn't have enough materials to build the spaceship.");
        }
        sb.append(System.lineSeparator());

        sb.append("Liquids left: ");
        if (liquids.isEmpty()) {
            sb.append("none");
        } else {
            sb.append(collectionToString(liquids));
        }
        sb.append(System.lineSeparator());

        sb.append("Physical items left: ");
        if (physicals.isEmpty()) {
            sb.append("none");
        } else {
            Collections.reverse(physicals);
            sb.append(collectionToString(physicals));
        }
        sb.append(System.lineSeparator());

        materials.forEach((material, count) ->
                sb.append(material.name).append(": ").append(count).append(System.lineSeparator()));

        System.out.println(sb.toString());
    }

    private static String collectionToString(Collection<?> collection) {
        return collection.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    private enum Material {
        GLASS("Glass", 25),
        ALUMINIUM("Aluminium", 50),
        LITHIUM("Lithium", 75),
        CARBON_FIBER("Carbon fiber", 100);

        private static final Map<Integer, Material> VALUES_MAP = Arrays.stream(Material.values())
                .collect(Collectors.toUnmodifiableMap(m -> m.value, m -> m));

        String name;
        Integer value;

        Material(String name, Integer value) {
            this.name = name;
            this.value = value;
        }

        public static Material fromValue(int value) {
            return VALUES_MAP.get(value);
        }

        public String getName() {
            return this.name;
        }

        public Integer getValue() {
            return this.value;
        }
    }
}
