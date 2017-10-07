package Lesson05ObjectsClassesAndAPIs.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Pr16DragonArmy {

    public static void main(String[] args) throws IOException {
        Map<String, Map<String, Double[]>> dragons = new LinkedHashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int dragonsToRead = Integer.parseInt(reader.readLine());
        while (dragonsToRead-- > 0) {
            String[] tokens = reader.readLine().split("\\s+");
            String type = tokens[0];
            String name = tokens[1];
            double damage = "null".equals(tokens[2]) ? 45d : Double.parseDouble(tokens[2]);
            double health = "null".equals(tokens[3]) ? 250d : Double.parseDouble(tokens[3]);
            double armor = "null".equals(tokens[4]) ? 10d : Double.parseDouble(tokens[4]);

            if (!dragons.containsKey(type)) {
                dragons.put(type, new TreeMap<>());
            }

            dragons.get(type).put(name, new Double[]{damage, health, armor});
        }

        dragons.forEach((type, members) -> {
            final Double[] avgStats = {0d, 0d, 0d};
            members.values().forEach(x -> avgStats[0] += x[0]);
            members.values().forEach(x -> avgStats[1] += x[1]);
            members.values().forEach(x -> avgStats[2] += x[2]);
            for (int i = 0; i < avgStats.length; i++) {
                avgStats[i] /= members.size();
            }

            System.out.printf("%s::(%.2f/%.2f/%.2f)%n", type, avgStats[0], avgStats[1], avgStats[2]);

            members.forEach((name, stats) ->
                    System.out.printf("-%s -> damage: %.0f, health: %.0f, armor: %.0f%n",
                            name, stats[0], stats[1], stats[2]));
        });
        System.out.println();
    }
}