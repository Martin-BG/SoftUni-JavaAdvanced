package ExamPractice.Exam2019June23.pr03SpaceStationRecruitment;

public class Astronaut {
    private String name;
    private int age;
    private String country;

    public Astronaut(String name, int age, String country) {
        this.name = name;
        this.age = age;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Astronaut: " + name + ", " + age + " (" + country + ')';
    }
}
