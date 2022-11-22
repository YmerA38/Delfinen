package org.example;

import java.time.LocalDate;

public class Member {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private boolean isActive;
    private boolean isCompeting;
    private boolean hasPayed;
    private LocalDate dateOfMembership;
    private int membershipNumber;
    private Team team;



    public Member(String firstName, String lastName, LocalDate dateOfBirth,boolean isActive,boolean isCompeting){
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.isActive = isActive;
        this.isCompeting = isCompeting;
        this.dateOfMembership = LocalDate.now();
    }

     public Team autoSetTeam(){

        if(isActive) {
            if (age() > 17) {
                if (isCompeting) {
                    return Team.SENIOR_COMPETE;
                } else {
                    return Team.SENIOR_FITNESS;
                }
            } else {
                if (isCompeting) {
                    return Team.JUNIOR_COMPETE;
                } else {
                    return Team.JUNIOR_FITNESS;
                }
            }
        }else {
            return Team.NO_TEAM;
        }

    }

    public int age(){
        int age = 0;
        //age = dateOfBirth - LocalDate.now();//TODO find metode
        return age;
    }
}
