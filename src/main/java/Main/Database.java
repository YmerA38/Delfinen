package Main;


import Program.Member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Database {
    private ArrayList<Member> memberList;

    public Database(){
        memberList = new ArrayList<>();
    }

    public ArrayList<Member> getMemberList() {
        return memberList;
    }

    public void addMember(Member member){
        memberList.add(member);
        member.setMembershipNumber(memberList.indexOf(member));
        member.autoSetTeam();
    }
    public void removeMember(Member member){
        memberList.remove(member);
    }
    public ArrayList<Member> findMemberByName(String search){
        String[] searchPart = search.split(" ");
        ArrayList<Member> resultList = new ArrayList<>();
        boolean found;
        for(Member member : memberList){
            found = false;
            for(int i = 0; i< searchPart.length; i++){
                    if(member.getLastName().contains(searchPart[i])||member.getFirstName().contains(searchPart[i])){
                     found = true;
                    }
            }
            if(found){
                resultList.add(member);
            }
        }
        return resultList;
    }


   /* public void sortMemberMethod() throws IOException{
        memberList.sort(new MemberComparator());
    }*/



}
