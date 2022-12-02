package Program;

import java.util.ArrayList;

public class Results {
    private Discipline discipline;
    private double time;
    private int distance;
    private String competitionName;

    public  ArrayList<Results> resultList;


    public Results(Discipline discipline, double time, int distance, String competitionName) {
        resultList = new ArrayList<>();
        resultList.add(new Results(Discipline.CRAWL,12.6,20,"DM"));
        resultList.add(new Results(Discipline.BUTTERFLY,5,50,"DM"));
        this.competitionName = competitionName;
        this.discipline = discipline;
        this.time = time;
        this.distance = distance;
    }


    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

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
