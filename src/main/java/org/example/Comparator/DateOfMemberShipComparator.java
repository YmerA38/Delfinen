package org.example.Comparator;

import Program.Member;

import java.util.Comparator;

public class DateOfMemberShipComparator implements Comparator<Member> {
    @Override
    public int compare(Member o1, Member o2) {
        return o1.getDateOfMembership().compareTo(o2.getDateOfMembership());
    }
}
