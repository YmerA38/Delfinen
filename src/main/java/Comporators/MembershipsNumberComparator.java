package Comporators;

import Program.Member;

import java.util.Comparator;

public class MembershipsNumberComparator implements Comparator<Member> {
    @Override
    public int compare(Member o1, Member o2) {
        return Integer.compare(o1.getMembershipNumber(), o2.getMembershipNumber());
    }
}
