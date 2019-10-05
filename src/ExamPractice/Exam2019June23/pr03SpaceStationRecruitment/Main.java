package ExamPractice.Exam2019June23.pr03SpaceStationRecruitment;

public class Main {

    public static void main(String[] args) {
        // Initialize the repository
        SpaceStation spaceStation = new SpaceStation("Apollo", 10);

        // Initialize entity
        Astronaut astronaut = new Astronaut("Stephen", 40, "Bulgaria");

        // Print Astronaut
        System.out.println(astronaut); // Astronaut: Stephen, 40 (Bulgaria)
        System.out.println(astronaut.toString().equals("Astronaut: Stephen, 40 (Bulgaria)"));
        // Add Astronaut
        spaceStation.add(astronaut);

        // Remove Astronaut
        spaceStation.remove("Astronaut name"); // false

        Astronaut secondAstronaut = new Astronaut("Mark", 34, "UK");
        // Add Astronaut
        spaceStation.add(secondAstronaut);

        Astronaut oldestAstronaut = spaceStation.getOldestAstronaut();
        // Astronaut with name Stephen

        Astronaut astronautStephen = spaceStation.getAstronaut("Stephen");
        // Astronaut with name Stephen

        // Print Astronauts
        System.out.println(oldestAstronaut); // Astronaut: Stephen, 40 (Bulgaria)
        System.out.println(oldestAstronaut.toString().equals("Astronaut: Stephen, 40 (Bulgaria)"));
        System.out.println(astronautStephen); // Astronaut: Stephen, 40 (Bulgaria)
        System.out.println(astronautStephen.toString().equals("Astronaut: Stephen, 40 (Bulgaria)"));

        System.out.println(spaceStation.getCount()); // 2
        System.out.println(spaceStation.getCount() == 2);
        System.out.println(spaceStation.report());
        System.out.println(spaceStation.report().equals("" +
                "Astronauts working at Space Station Apollo:\r\n" +
                "Astronaut: Stephen, 40 (Bulgaria)\r\n" +
                "Astronaut: Mark, 34 (UK)"));
        // Astronauts working at Space Station Apollo:
        // Astronaut: Stephen, 40 (Bulgaria)
        // Astronaut: Mark, 34 (UK)
    }
}
