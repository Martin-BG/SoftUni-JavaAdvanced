package Lesson05ObjectsClassesAndAPIs.Exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Pr15LegendaryFarming {

    public static void main(String[] args) throws IOException {
        final int REQUIRED = 250;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> materials = new TreeMap<>();
        materials.put("shards", 0);
        materials.put("fragments", 0);
        materials.put("motes", 0);

        while (true) {
            String[] input = reader.readLine().toLowerCase().split("\\s+");

            for (int i = 0; i < input.length; i += 2) {
                int quantity = Integer.parseInt(input[i]);
                String material = input[i + 1];

                materials.compute(material, (k, v) -> (v == null) ? quantity : (v + quantity));

                if (isKeyMaterial(material) && materials.get(material) >= REQUIRED) {
                    System.out.printf("%s obtained!%n", getItemName(material));

                    materials.compute(material, (k, v) -> (v - REQUIRED));
                    materials.entrySet().stream().filter(entry -> isKeyMaterial(entry.getKey()))
                            .sorted((mat1, mat2) -> mat2.getValue().compareTo(mat1.getValue()))
                            .forEachOrdered(mat -> {
                                System.out.printf("%s: %d%n", mat.getKey(), mat.getValue());
                                materials.remove(mat.getKey());
                            });
                    materials.forEach((mat, val) -> System.out.printf("%s: %d%n", mat, val));

                    return;
                }
            }
        }
    }

    private static String getItemName(String material) {
        switch (material) {
        case "shards":
            return "Shadowmourne";
        case "fragments":
            return "Valanyr";
        case "motes":
            return "Dragonwrath";
        default:
            return "";
        }
    }

    private static boolean isKeyMaterial(String material) {
        return "shards".equals(material) ||
                "fragments".equals(material) ||
                "motes".equals(material);
    }
}