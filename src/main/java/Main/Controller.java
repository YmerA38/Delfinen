package Main;

import Program.*;
import UI.UI;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Controller {

    FileHandler fileHandler = new FileHandler();
    static Database database = new Database();
    Scanner scan = new Scanner(System.in);


    public void addMember(Member member){
        database.addMember(member,true);
    }

    public void editMember() {
        try {
            System.out.println("Her er starten på metoden til at redigere medlemmets oplysninger");
            String searchTerm = scan.next();
            scan.nextLine();
            ArrayList<Member> searchResult = new ArrayList<>();
            for (Member member : database.getMemberList()) { //TODO det giver ingen mening, da findMember by name allerede returnerer en liste
                if (member.getFirstName().contains(member.getFirstName()) || member.getLastName().contains(member.getLastName())) {
                    searchResult.add(member);
                    System.out.println(member);
                }
                if (!searchResult.isEmpty()) {
                    System.out.println("Der er ikke noget medlem med det fornavn eller efternavn");
                } else {
                    System.out.println("Fundet matches med søgt navn");
                    for (int i = 0; i < searchResult.size(); i++) {
                        System.out.println(((i) + 1) + ")" + searchResult.get((i)));
                    }
                    System.out.println("Vælg navnet på matchene navne du vil redigere");
                    int number = scan.nextInt();
                    scan.nextLine();
                    Member member1 = searchResult.get(number - 1);

                    System.out.println("Skriv fornavnet på det medlem du vil redigere");
                    String firstName = scan.nextLine();

                    if (!firstName.isEmpty()) {
                        member.setFirstName(firstName);
                    }
                    System.out.println("Skriv efternavnet på det medlem du vil redigere");
                    String lastName = scan.nextLine();

                    if (!lastName.isEmpty()) {
                        member.setLastName(lastName);
                    }

                }


            }


        } catch (Exception e) {
            System.out.println("");
        }

    }

    public void deleteMember() {


        System.out.println("Skriv fornavnet eller efternavnet på det medlem du vil slette");
        String searchTerm = scan.next();
        ArrayList<Member> searchResult = database.getMemberList();
        if (!searchResult.isEmpty()) {
            System.out.println("Ingen resultater fundet");
        } else {
            for (int i = 0; i < searchResult.size(); i++) {
                System.out.println((i) + 1 + ")" + searchResult.get(i));
            }
            System.out.println("Vælg navnet på medlemmet du vil slette");
            int number = scan.nextInt();
            scan.nextLine();
            Member member = searchResult.get(number - 1);

            System.out.println("Do you want to erase this superhero? (True/False: " + member);
            boolean delete = scan.nextBoolean();
            if (delete == true) {
                database.removeMember(member);
                System.out.println("Erasing the superhero...");
            } else if (delete == false) {
                System.out.println("Doesnt erase the superhero" + member);

                System.out.println("Her skal man kunne fjerne et medlem");
            }


        }
        System.out.println("Her skal man kunne fjerne et medlem");




    }
    public ArrayList<Results> getResultList(){
        return database.getResultList();
    }

    public ArrayList<Member> getMemberList () {
        return database.getMemberList();
    }

    public Access login(String name, String password){
        return database.login(name,password);
    }


    public void save() throws FileNotFoundException {
        fileHandler.save(database.getMemberList());
        fileHandler.saveResult(database.getResultList());
    }


    public void load() throws FileNotFoundException {
        fileHandler.load(database);
    }


    public ArrayList<Member> findMemberByName(String searchTerm) {
        return database.findMemberByName(searchTerm);
    }


    public double getTotalPayment() {
        database.autoSetPayments();
        return database.totalIncome();
    }

    public String updatePayments() {
        String message = "";
        for (Member member: database.getMemberList()) {
            database.updatePaymets(member);
            if(fileHandler.savePayment(member)){
                message += "Payment for "+member.getFirstName()+" "+member.getLastName()+ " SAVED to file\n";
            }else{
                message += "No payment file for "+member.getFirstName()+" "+member.getLastName()+ " ----\n";
            }
        }
       return message;
    }
}


