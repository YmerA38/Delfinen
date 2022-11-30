package Program;

import java.time.LocalDate;

import static Program.Subscription.database;

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
    private String username;
    private String password;
    private Users userType;

    private int payment;
    private int subscribtionRate;

    // denne constructer bruges af Formand
    public Member(String firstName, String lastName, LocalDate dateOfBirth, boolean isActive, boolean isCompeting, Users userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.isActive = isActive;
        this.isCompeting = isCompeting;
        this.dateOfMembership = LocalDate.now();
        this.password = "1234";
        this.userType = userType;
    }

    public Member(Member member) {
        this.firstName = member.getFirstName();
        this.lastName = member.getLastName();
        this.dateOfBirth = member.getDateOfBirth();
        this.isActive = member.getIsActive();
        this.isCompeting = member.getIsCompeting();
        this.dateOfMembership = member.getDateOfMembership();
        this.userType = member.getUserType();
    }


        // denne contrukter er til brug for fileHandler
    public Member(String firstName, String lastName, LocalDate dateOfBirth,boolean isActive,boolean isCompeting,
    boolean hasPayed,LocalDate dateOfMembership,int membershipNumber,Team team,String username,String password,Users userType){
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.isActive = isActive;
        this.isCompeting = isCompeting;
        this.hasPayed = hasPayed;
        this.dateOfMembership = dateOfMembership;
        this.membershipNumber = membershipNumber;
        this.team = team;
        this.username = username;
        this.password = password;
        this.userType = userType;

       this.payment = payment;
       this.subscribtionRate = getSubscribtionRate();
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


    public void getSubscriptions() {
        for (Member member : database.getMemberList()) {
            if (isActive == true) {
                if (member.age() < 18) {
                    payment = 1000;
                } else if (member.age() <= 60) {
                    payment = 1600;
                } else if (member.age() > 60) {
                    payment = 1200;
                }
            } else {
                payment = 500;
            }
        }
    }
    public int getSubscribtionRate() {
        return subscribtionRate;
    }

    public void setSubscribtionRate(){
        int age = age();
        this.subscribtionRate = this.isActive == false ? 500 : age < 18 ? 1000 : age > 60 ? 1200 : 1600; //skal rettes
    }



    public void autoSetUserName(){
        this.username = ""+membershipNumber; // midlertidig username;
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

    public LocalDate getDateOfBirth(){
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

    public boolean getIsCompeting() {
        return isCompeting;
    }

    public void setCompeting(boolean competing) {
        isCompeting = competing;
    }

    public boolean getHasPayed() {
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

    public Users getUserType() {
        return userType;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(Users userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString(){
        return "Medlem: " + "\nFornavn: " + firstName + "\nEfternavn: " + lastName + "\nAlder: " + dateOfBirth + "\nStatus: " + isActive + "\nKonkurrence: " + isCompeting + "\nBetaling status: " + hasPayed + "\nDato for indmeldelse: " + dateOfMembership + "\nMedlemsnummer: " + membershipNumber+ "\nHold: " + team;
    }
}
