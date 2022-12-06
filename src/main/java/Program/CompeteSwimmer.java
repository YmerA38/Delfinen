package Program;

import java.time.LocalDate;
import java.util.AbstractList;
import java.util.ArrayList;

public class CompeteSwimmer extends Member{
    public static ArrayList<Results> getResultList;
    private AbstractList<Results> resultList;
    public CompeteSwimmer(Member member) {
        super(member);
        resultList = new ArrayList<>();
    }

    public AbstractList<Results> getResultList() {
        return resultList;
    }

    public void addResult(Discipline discipline, double time, int distance, String competitionName){
        resultList.add(new Results(Discipline.CRAWL,12.6,20,"DM"));
        resultList.add(new Results(Discipline.BUTTERFLY,5,50,"DM"));
        resultList.add(new Results(discipline,time,distance,competitionName));
    }
}
