package org.example.Comparator;

import Program.Member;

import java.util.Comparator;

public class LastNameComparator implements Comparator<Member> {
    @Override
    public int compare(Member o1, Member o2) {
       return o1.getLastName().compareTo(o2.getLastName());
    }
}
