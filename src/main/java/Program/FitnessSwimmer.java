package Program;

import java.util.AbstractList;
import java.util.ArrayList;

public class FitnessSwimmer extends Member {
    private AbstractList<Results> resultList;
    public FitnessSwimmer(Member member) {
        super(member);
        resultList = new ArrayList<>();
    }
    public void addResult(Discipline discipline,double time,int distance){
        resultList.add(new Results(discipline,time,distance));
    }

}
