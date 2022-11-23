package org.example.Comparator;

import Program.Member;

import java.util.Comparator;

public class HasPayedComparator implements Comparator<Member> {
    @Override
    public int compare(Member o1, Member o2) {
        return Boolean.compare(o1.getHasPayed(), o2.getHasPayed());
    }
}
