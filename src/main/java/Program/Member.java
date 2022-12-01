package Program;

import java.time.LocalDate;
import java.util.ArrayList;

import static Program.Subscription.database;

//import static Program.Subscription.database;

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
    private LocalDate nextPayment;

    private double subscriptionRate;
    private ArrayList<AccountTransaction> paymentBalances;


    // denne constructer bruges af Formand
    public Member(String firstName, String lastName, LocalDate dateOfBirth, boolean isActive, boolean isCompeting,
                  boolean hasPayed, Users userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.isActive = isActive;
        this.isCompeting = isCompeting;
        this.hasPayed = hasPayed;
        this.dateOfMembership = LocalDate.now();
        this.password = "1234";
        this.userType = userType;
        this.paymentBalances = new ArrayList<>();

    }

    public Member(Member member) {
        this.firstName = member.getFirstName();
        this.lastName = member.getLastName();
        this.dateOfBirth = member.getDateOfBirth();
        this.isActive = member.getIsActive();
        this.isCompeting = member.getIsCompeting();
        this.hasPayed = member.getHasPayed();
        this.dateOfMembership = member.getDateOfMembership();
        this.membershipNumber = member.getMembershipNumber();
        this.team = member.getTeam();
        this.username = member.getUsername();
        this.password = member.getPassword();
        this.userType = member.getUserType();




    }


    // denne contrukter er til brug for fileHandler
    public Member(String firstName, String lastName, LocalDate dateOfBirth, boolean isActive, boolean isCompeting,
                  boolean hasPayed, LocalDate dateOfMembership, int membershipNumber, Team team, String username, String password, Users userType) {
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

    }

    public void autoSetTeam() {

        if (isActive) {
            if (age() > 17) {
                if (isCompeting) {
                    this.team = Team.SENIOR_COMPETE;
                } else {
                    this.team = Team.SENIOR_FITNESS;
                }
            } else {
                if (isCompeting) {
                    this.team = Team.JUNIOR_COMPETE;
                } else {
                    this.team = Team.JUNIOR_FITNESS;
                }
            }
        } else {
            this.team = Team.NO_TEAM;
        }

    }


    public int age() {

        int age = LocalDate.now().getYear() - dateOfBirth.getYear();
        if (dateOfBirth.getMonth().getValue() > LocalDate.now().getMonth().getValue()) {
            age -= 1;
        }
        if (dateOfBirth.getMonth().getValue() == LocalDate.now().getMonth().getValue()) {
            if (dateOfBirth.getDayOfMonth() > LocalDate.now().getDayOfMonth()) {
                age -= 1;
            }
        }
        return age;
    }





    public void autoSetUserName() {
        this.username = firstName; // midlertidig username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
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

    public void autoSetPayment() {
        double payment = 0;
            if (getIsActive() == true) {
                if (age() < 18) {
                    payment = 1000;
                } else if (age() <= 60) {
                    payment = 1600;
                } else if (age() > 60) {
                    payment = 1200;
                }
            } else {
                payment = 500;
            }
        this.subscriptionRate = payment;
    }

    public void setSubscriptionRate(double subscriptionRate) {
        this.subscriptionRate = subscriptionRate;
    }

    public double getSubscriptionRate() {
        return subscriptionRate;
    }

    @Override
    public String toString() {
        return "Medlem: " + "\nFornavn: " + firstName + "\nEfternavn: " + lastName + "\nAlder: " + dateOfBirth +
                "\nStatus: " + isActive + "\nKonkurrence: " + isCompeting + "\nBetaling status: " + hasPayed +
                "\nDato for indmeldelse: " + dateOfMembership + "\nMedlemsnummer: " + membershipNumber +
                "\nHold: " + team + "\nBrugernavn " + username + "\nKode " + password;
    }


    public void payment(double payment){
        AccountTransaction payAccount = new AccountTransaction();
        payAccount.setPayment(payment);
        paymentBalances.add(payAccount);
    }
    public void putSubscription(){
        AccountTransaction debtAccount = new AccountTransaction();
        debtAccount.setSubscription(subscriptionRate,LocalDate.of(LocalDate.now().getYear(),dateOfMembership.getMonth(),
                dateOfMembership.getDayOfMonth()));
        paymentBalances.add(debtAccount);
    }


    public double getBallance() {
        double balance = 0;
        if(!paymentBalances.isEmpty()){
            for(AccountTransaction transaction: paymentBalances){
                balance += transaction.getPayment()-transaction.getSubscription();
            }
        }
        return balance;
    }

    public void setNextPayment(LocalDate nextPayment) {
        this.nextPayment = nextPayment;
    }

    public LocalDate getNextPayment() {
        return nextPayment;
    }
}
