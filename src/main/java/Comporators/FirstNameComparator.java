package Comporators;

import Program.Member;

import java.util.Comparator;

public class FirstNameComparator implements Comparator<Member> {
    @Override
    public int compare(Member o1, Member o2) {
        return o1.getFirstName().toLowerCase().compareTo(o2.getFirstName());
    }
}
