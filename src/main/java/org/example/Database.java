package org.example;


import java.util.ArrayList;

public class Database {
    private ArrayList<Member> memberList;

    public Database(){
        memberList = new ArrayList<>();
    }

    public void addMember(Member member){
        memberList.add(member);
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



}
