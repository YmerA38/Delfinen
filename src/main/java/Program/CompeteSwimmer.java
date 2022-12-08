package Program;

import java.time.LocalDate;
import java.util.AbstractList;
import java.util.ArrayList;

public class CompeteSwimmer extends Member{
    public static ArrayList<Results> getResultList;
    private AbstractList<Results> resultList;
    public CompeteSwimmer(String firstName, String lastName, LocalDate dateOfBirth, boolean isActive, boolean isCompeting,
                          Users userType) {
        super(firstName,lastName,dateOfBirth,isActive,isCompeting,userType);
        resultList = new ArrayList<>();
    }

    public CompeteSwimmer(String firstName, String lastName, LocalDate dateOfBirth, boolean isActive, boolean isCompeting,
                          Users userType, boolean hasPayed, LocalDate dateOfMembership, int membershipNumber, Team team,
                          String username, String password, LocalDate nextPayment) {
        super(firstName,lastName,dateOfBirth,isActive,isCompeting,hasPayed,dateOfMembership,membershipNumber,team,
                username,password,userType,nextPayment);
        resultList = new ArrayList<>();
    }


    public AbstractList<Results> getResultList() {
        return resultList;
    }

    public void addResult(Discipline discipline, double time, int distance, String competitionName){
        resultList.add(new Results(discipline,time,distance,competitionName));
        //resultList.add(new Results(Discipline.BUTTERFLY,5,50,"DM"));
        //resultList.add(new Results(discipline,time,distance,competitionName));
    }
}
