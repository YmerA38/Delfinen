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
    public Member(String firstName, String lastName, LocalDate dateOfBirth,boolean isActive,boolean isCompeting,
    boolean hasPayed,LocalDate dateOfMembership,int membershipNumber,Team team){
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.isActive = isActive;
        this.isCompeting = isCompeting;
        this.hasPayed = hasPayed;
        this.dateOfMembership = dateOfMembership;
        this.membershipNumber = membershipNumber;
        this.team = team;
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

        int age = dateOfBirth.getYear() - LocalDate.now().getYear();
        if(dateOfBirth.getMonth().getValue()>LocalDate.now().getMonth().getValue()){
            age -= 1;
        }
        if(dateOfBirth.getMonth().getValue()==LocalDate.now().getMonth().getValue()) {
            if(dateOfBirth.getDayOfMonth()>LocalDate.now().getDayOfMonth()){
                age -= 1;
            }
        }
        return age;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth(LocalDate dateOfBirth){
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }


    public boolean getIsActive(){
        return isActive;
    }

    public void setIsActive(boolean isActive){
        this.isActive = isActive;
    }

    public boolean isCompeting() {
        return isCompeting;
    }

    public void setCompeting(boolean competing) {
        isCompeting = competing;
    }

    public boolean isHasPayed() {
        return hasPayed;
    }

    public void setHasPayed(boolean hasPayed) {
        this.hasPayed = hasPayed;
    }

    public LocalDate getDateOfMembership() {
        return dateOfMembership;
    }

    public void setDateOfMembership(LocalDate dateOfMembership) {
        this.dateOfMembership = dateOfMembership;
    }

    public int getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(int membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
