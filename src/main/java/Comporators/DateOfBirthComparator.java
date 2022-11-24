package Comporators;

import Program.Member;

import java.util.Comparator;

public class DateOfBirthComparator implements Comparator<Member> {
    @Override
    public int compare(Member o1, Member o2) {
        return o1.getDateOfBirth().compareTo(o2.getDateOfBirth());
    }
}
