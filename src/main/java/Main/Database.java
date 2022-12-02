package Main;


import Program.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import Program.Subscription;


public class Database {
    private static ArrayList<Member> memberList;
    private ArrayList<Results> resultList;


    public Database(){
        memberList = new ArrayList<>();
        //memberList.add(new Member("Stinus","Helweg Andersen", LocalDate.of(1975,1,21),true,true,true,LocalDate.now(),2,Team.SENIOR_COMPETE,"Stinus","1234",Users.CHAIRMAN));  // TODO midlertidig

    }

    public ArrayList<Member> getMemberList() {
        return memberList;
    }
    public ArrayList<Results> getResultList(){
        return resultList;
    }

    public void addMember(Member member, boolean isNew){
        if(member.getIsCompeting()) {
           member = new CompeteSwimmer(member);
        }else {
            member = new FitnessSwimmer(member);
        }
        memberList.add(member);
        member.autoSetTeam();
        if(isNew){
            autoSet(member);
        }

    }
    public void autoSet(Member member){

        member.autoSetUserName();
        member.setMembershipNumber(memberList.indexOf(member));
        member.setNextPayment(member.getDateOfMembership());
    }



    public void removeMember(Member member){
        memberList.remove(member);
    }
    public ArrayList<Member> findMemberByName(String search){
        ArrayList<Member> searchResult = new ArrayList<>();

        String[] searchPart = search.split(" ");
        //ArrayList<Member> resultList = new ArrayList<>();
        ArrayList<RateMember> ratingList = new ArrayList<>();
        boolean found;
        for(Member member : memberList){
            found = false;
            for(int i = 0; i< searchPart.length; i++){
                    if(member.getLastName().contains(searchPart[i])||member.getFirstName().contains(searchPart[i])){
                     found = true;
                    }
            }
            if(found){
                RateMember rateMember = new RateMember(member,1);
                ratingList.add(rateMember);
                if(member.getFirstName().equals(searchPart[0])){
                    rateMember.addToRating(1);
                }
                String[] lastNamePart = member.getLastName().split(" ");
                for(int i = 1; i > searchPart.length; i++){
                    if(member.getLastName().contains(searchPart[i])){
                        rateMember.addToRating(1);
                    }
                    if(lastNamePart[i-1].equals(searchPart[i])){
                        rateMember.addToRating(1);
                    }
                }
            }
        }
        int highestScore = 0;
        for(RateMember rateMember : ratingList){
            if(rateMember.getRating()>highestScore){
                highestScore = rateMember.getRating();
            }
        }
        for(RateMember rateMember : ratingList){
            if(rateMember.getRating()==highestScore){
                searchResult.add(rateMember.getMember());
            }
        }
        return searchResult;
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
                access.setUserType(Users.WRONG_PASSWORD);
            }
        } else {
            access.setUserType(Users.NO_USER);
        }
        return access;
    }

   public void autoSetPayments(){
        for (Member member : memberList){
            member.autoSetPayment();
        }
   }

   public double totalIncome(){
        double total = 0;
        for (Member member : memberList){
            total +=member.getSubscriptionRate();
        }
        return total;
   }


    public void updatePaymets(Member member) {

        if(LocalDate.now().isAfter(member.getNextPayment())){
            member.putSubscription();
            member.setNextPayment(LocalDate.of(LocalDate.now().getYear()+1,member.getDateOfMembership().getMonth(),
                                    member.getDateOfMembership().getDayOfMonth()));
        }
            if(member.getBallance()>=0){
                member.setHasPayed(true);
            }else{
                member.setHasPayed(false);
            }

    }
}

   /* public void sortMemberMethod() throws IOException{
        memberList.sort(new MemberComparator());
    }*/




