package Program;

import java.time.LocalDate;
import java.util.AbstractList;
import java.util.ArrayList;

public class FitnessSwimmer extends Member {

    public FitnessSwimmer(String firstName, String lastName, LocalDate dateOfBirth, boolean isActive, boolean isCompeting, Users userType) {
        super(firstName,lastName,dateOfBirth,isActive,isCompeting,userType);

    }


    public FitnessSwimmer(String firstName, String lastName, LocalDate dateOfBirth, boolean isActive, boolean isCompeting,
                          Users userType, boolean hasPayed, LocalDate dateOfMembership, int membershipNumber, Team team,
                          String username, String password, LocalDate nextPayment) {
        super(firstName,lastName,dateOfBirth,isActive,isCompeting,hasPayed,dateOfMembership,membershipNumber,team,
                username,password,userType,nextPayment);
    }
}
