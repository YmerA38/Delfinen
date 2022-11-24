package Comporators;

import Program.Member;

import java.util.Comparator;

public class TeamComparator implements Comparator<Member> {
    @Override
    public int compare(Member o1, Member o2) {
        return o1.getTeam().compareTo(o2.getTeam());
    }
}
