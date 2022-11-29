package Sort;

import Program.Member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Sort {

  public void sortByFirstname(ArrayList<Member> memberlist) {
    if (memberlist != null) {
      Collections.sort(memberlist, Comparator.comparing(Member :: getFirstName));
    } else System.out.println("Der er ingen medlemmer på listen til at sortere");
  }

  public void sortByLastname(ArrayList<Member> memberlist) {
    if (memberlist != null) {
      Collections.sort(memberlist, Comparator.comparing(Member :: getLastName));
    } else System.out.println("Der er ingen medlemmer på listen til at sortere");
  }

  public void sortByAge(ArrayList<Member> memberlist) {
    if (memberlist != null) {
      Collections.sort(memberlist, Comparator.comparing(Member :: getDateOfBirth));
    } else System.out.println("Der er ingen medlemmer på listen til at sortere");
  }


  public void sortByActive(ArrayList<Member> memberlist) {
    if (memberlist != null) {
      Collections.sort(memberlist, Comparator.comparing(Member :: getIsActive));
    } else System.out.println("Der er ingen medlemmer på listen til at sortere");
  }

  public void sortByCompetitive(ArrayList<Member> memberlist) {
    if (memberlist != null) {
      Collections.sort(memberlist, Comparator.comparing(Member :: getIsCompeting));
    } else System.out.println("Der er ingen medlemmer på listen til at sortere");
  }

  public void sortByPayed(ArrayList<Member> memberlist) {
    if (memberlist != null) {
      Collections.sort(memberlist, Comparator.comparing(Member :: getHasPayed));
    } else System.out.println("Der er ingen medlemmer på listen til at sortere");
  }

  public void sortByDateOfMembership(ArrayList<Member> memberlist) {
    if (memberlist != null) {
      Collections.sort(memberlist, Comparator.comparing(Member :: getDateOfMembership));
    } else System.out.println("Der er ingen medlemmer på listen til at sortere");
  }

  public void sortByMembership(ArrayList<Member> memberlist) {
    if (memberlist != null) {
      Collections.sort(memberlist, Comparator.comparing(Member :: getMembershipNumber));
    } else System.out.println("Der er ingen medlemmer på listen til at sortere");
  }

  public void sortByTeam(ArrayList<Member> memberlist) {
    if (memberlist != null) {
      Collections.sort(memberlist, Comparator.comparing(Member :: getTeam));
    } else System.out.println("Der er ingen medlemmer på listen til at sortere");
  }

  public void sortByUsertype(ArrayList<Member> memberlist){
    if (memberlist != null) {
      Collections.sort(memberlist, Comparator.comparing(Member::getUserType));
    } else System.out.println("Der er ingen medlemmer på listen til at sortere");
  }
}

