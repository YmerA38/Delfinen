package org.example.Comparator;

import Program.Member;
import java.util.Comparator;

public class IsCompetingComparator implements Comparator<Member> {
    @Override
    public int compare(Member o1, Member o2) {
        return Boolean.toString(o1.getIsCompeting()).compareTo(Boolean.toString(o2.getIsCompeting()));
    }
}
