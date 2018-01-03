package PracticalExamRetake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Pr03TheDharmaInitiative {

    private static final String END_RECRUIT = "Recruit";
    private static final String RECRUIT_STRING = "###%s - %d";
    private static final String SPLIT_STRING = ":";
    private static final String HYDRA = "Hydra";
    private static final String ARROW = "Arrow";
    private static final String FLAME = "Flame";
    private static final String PEARL = "Pearl";
    private static final String ORCHID = "Orchid";
    private static final String DHARMA_INITIATIVE_COMMAND = "DHARMA Initiative";
    private static final String INVALID_STATION_NAME = "DHARMA Initiative does not have such a station!";
    private static final String DHARMA_REPORT = "The %s has %d DHARMA recruits in it.%n";
    private static final String NO_RECRUITS = "No recruits.";
    private static final String STATION_TEXT = "The %s station: %s%n";
    private static final String HYDRA_DESC = "Zoological Research.";
    private static final String ARROW_DESC = "Development of defensive strategies, and Intelligence gathering.";
    private static final String FLAME_DESC = "Communication.";
    private static final String PEARL_DESC = "Psychological Research and/or Observation.";
    private static final String ORCHID_DESC = "Space-time manipulation research, disguised as a Botanical station.";

    private static final Map<String, String> DESCRIPTIONS;
    private static final Map<String, Set<Recruit>> STATIONS;

    static {
        DESCRIPTIONS = new HashMap<String, String>() {{
            put(HYDRA, HYDRA_DESC);
            put(ARROW, ARROW_DESC);
            put(FLAME, FLAME_DESC);
            put(PEARL, PEARL_DESC);
            put(ORCHID, ORCHID_DESC);
        }};

        STATIONS = new HashMap<String, Set<Recruit>>() {{
            put(HYDRA, new TreeSet<>());
            put(ARROW, new TreeSet<>());
            put(FLAME, new TreeSet<>());
            put(PEARL, new TreeSet<>());
            put(ORCHID, new TreeSet<>());
        }};
    }

    public static void main(String[] args) {

        String input;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            while (!END_RECRUIT.equalsIgnoreCase(input = reader.readLine())) {
                String[] tokens = input.split(SPLIT_STRING);

                String station = tokens[2];
                if (STATIONS.containsKey(station)) {
                    long id = Long.parseLong(tokens[1]);
                    String name = tokens[0];
                    STATIONS.get(station).add(new Recruit(id, name));
                }
            }

            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (DHARMA_INITIATIVE_COMMAND.equalsIgnoreCase(input)) {

            STATIONS.entrySet().stream()
                    .sorted(Comparator
                            .comparingInt((Map.Entry<String, Set<Recruit>> kvp) -> kvp.getValue().size()).reversed()
                            .thenComparing(Map.Entry::getKey))
                    .forEach(kvp -> System.out.printf(DHARMA_REPORT, kvp.getKey(), kvp.getValue().size()));

        } else if (STATIONS.containsKey(input)) {

            System.out.printf(STATION_TEXT, input, DESCRIPTIONS.get(input));

            if (STATIONS.get(input).isEmpty()) {
                System.out.println(NO_RECRUITS);
            } else {
                STATIONS.get(input).forEach(System.out::println);
            }

        } else {
            System.out.println(INVALID_STATION_NAME);
        }
    }

    private static class Recruit implements Comparable<Recruit> {

        private long id;
        private String name;

        Recruit(long id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format(RECRUIT_STRING, this.name, this.id);
        }

        @Override
        public int compareTo(Recruit o) {
            return Long.compare(o.id, this.id);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Recruit recruit = (Recruit) o;

            return id == recruit.id && name.equals(recruit.name);
        }

        @Override
        public int hashCode() {
            return (int) (id ^ (id >>> 32));
        }
    }
}
