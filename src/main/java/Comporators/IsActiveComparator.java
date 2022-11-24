package Comporators;

import Program.Member;

import java.util.Comparator;

public class IsActiveComparator implements Comparator<Member> {
    @Override
    public int compare(Member o1, Member o2) {
        return Boolean.compare(o1.getIsActive(),o2.getIsActive() );
    }
}
