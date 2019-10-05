package ExamPractice.Exam2019June23.pr03SpaceStationRecruitment;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class SpaceStation {
    private String name;
    private int capacity;
    private Map<String, Astronaut> data;

    public SpaceStation(String name, int capacity) {
        this.capacity = capacity;
        this.name = name;
        data = new LinkedHashMap<>();
    }

    public String getName() {
        return name;
    }

    public void add(Astronaut astronaut) {
        if (getCapacity() > getCount()) {
            data.putIfAbsent(astronaut.getName(), astronaut);
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCount() {
        return data.size();
    }

    public boolean remove(String name) {
        return data.remove(name) != null;
    }

    public Astronaut getOldestAstronaut() {
        return data.values()
                .stream()
                .max(Comparator.comparingInt(Astronaut::getAge))
                .orElse(null);
    }

    public Astronaut getAstronaut(String name) {
        return data.get(name);
    }

    public String report() {
        final StringBuilder sb = new StringBuilder("Astronauts working at Space Station ")
                .append(name).append(":").append(System.lineSeparator());

        data.values()
                .forEach(astronaut -> sb.append(astronaut).append(System.lineSeparator()));

        return sb.toString().trim();
    }
}
