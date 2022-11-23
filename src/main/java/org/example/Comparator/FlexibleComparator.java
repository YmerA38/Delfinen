package org.example.Comparator;

import java.util.Comparator;
import Program.Member;

public class FlexibleComparator implements Comparator<Member> {
    private int sortBy;

    public FlexibleComparator(int sortBy){
        this.sortBy = sortBy;
    }

    @Override
    public int compare(Member o1, Member o2) {
        switch(sortBy){
            case 1 -> {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }case 2 -> {
                return o1.getLastName().compareTo(o2.getLastName());
            }case 3 ->{
                return o1.getDateOfBirth().compareTo(o2.getDateOfBirth());
            }case 4 ->{
                return Boolean.compare(o1.getIsActive(),o2.getIsActive() );
            } case 5 ->{
                return Boolean.compare(o1.getIsCompeting(),o2.getIsCompeting());
            } case 6 -> {
                return Boolean.compare(o1.getHasPayed(), o2.getHasPayed());
            }case 7->{
                return o1.getDateOfMembership().compareTo(o2.getDateOfMembership());
            }case 8->{
                return Integer.compare(o1.getMembershipNumber(), o2.getMembershipNumber());
            }case 9->{
                return o1.getTeam().compareTo(o2.getTeam());
            }
        }

        return 0;
    }
}
