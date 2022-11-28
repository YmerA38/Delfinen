package Main;


import Program.*;

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
        if(member.getIsCompeting()) {
            memberList.add(new CompeteSwimmer(member));
        }else {
            memberList.add(new FitnessSwimmer(member));
        }
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
    public Member searchUserName(String name){
        for (Member member : memberList){
            if (name.equals(member.getUsername())){
                return member;
            }
        }
        return null;
    }
    public Access login(String name,String code){
        Member member = searchUserName(name);
        Access access = new Access();
        access.setMember(member);
       if(member!=null){
            if(member.getPassword().equals(code)){
                 access.setUserType(member.getUserType());
            }else {
                member.setUserType(Users.WRONG_PASSWORD);
            }
        } else {
            member.setUserType(Users.NO_USER);
        }
        return access;
    }


   /* public void sortMemberMethod() throws IOException{
        memberList.sort(new MemberComparator());
    }*/



}
