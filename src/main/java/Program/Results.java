package Program;

public class Results {
    private Discipline discipline;
    private double time;
    private int distance;

    public Discipline getDiscipline() {
        return discipline;
    }

    public double getTime() {
        return time;
    }

    public int getDistance() {
        return distance;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
