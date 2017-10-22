package ExamPreparation02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Pr04FootballStats {

    public static void main(String[] args) {
        Map<String, Map<String, List<String>>> results = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = reader.readLine();
            while (!"season end".equalsIgnoreCase(input)) {
                String[] tokens = input.split(" - | result |:");
                String firstTeam = tokens[0];
                String secondTeam = tokens[1];
                String firstTeamRecord = String.format("%s - %s -> %s:%s", firstTeam, secondTeam, tokens[2], tokens[3]);
                String secondTeamRecord = String.format("%s - %s -> %s:%s", secondTeam, firstTeam, tokens[3], tokens[2]);

                results.putIfAbsent(firstTeam, new TreeMap<>());
                results.putIfAbsent(secondTeam, new TreeMap<>());
                results.get(firstTeam).putIfAbsent(secondTeam, new LinkedList<>());
                results.get(secondTeam).putIfAbsent(firstTeam, new LinkedList<>());
                results.get(firstTeam).get(secondTeam).add(firstTeamRecord);
                results.get(secondTeam).get(firstTeam).add(secondTeamRecord);

                input = reader.readLine();
            }

            String[] teams = reader.readLine().split(",\\s+");
            for (String team : teams) {
                results.get(team).values().forEach(x -> x.forEach(System.out::println));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}