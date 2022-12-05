package Program;

import java.time.LocalDate;
import java.util.AbstractList;
import java.util.ArrayList;

public class CompeteSwimmer extends Member{
    private AbstractList<Results> resultList;
    public CompeteSwimmer(Member member) {
        super(member);
        resultList = new ArrayList<>();
    }
    public void addResult(Discipline discipline,double time,int distance, String competitionName){
        resultList.add(new Results(discipline,time,distance,competitionName));
    }
}
