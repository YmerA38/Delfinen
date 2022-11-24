package Main;

import Program.FileHandler;
import Program.Member;
import UI.UI;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Controller {
    public static FileHandler fileHandler;
    //FileHandler fileHandler = new FileHandler();
    static Database database;
    static Scanner scan = new Scanner(System.in);

    //UI ui = new UI(); // Kan ikke køre hvsi denne ikke er slået fra.

   /* public void runProgram() throws FileNotFoundException {
        scan.nextLine();
        boolean isRunning = true;
        while (isRunning) {
            ui.startMenu();
            String command = scan.nextLine();

            switch (command) {
                case "1" -> addMember();
                case "2" -> editMember();
                case "3" -> deleteMember();
                case "4" -> viewMemberList();
                case "5" -> fileHandler.load();
                case "6" -> fileHandler.save(database.getMemberList());
                *//*
                case "7" ->
                case "8" ->
                case "9" ->
                case "0" ->

                 *//*


                default -> ui.invalidInput();
            }


        }


    }*/

    public static void addMember() {
        try {
            System.out.println();
            System.out.println("Indtast dit fornavn: ");
            String firstName = scan.next();
            scan.nextLine();
            System.out.println("Indtast dit efternavn: ");
            String lastName = scan.next();
            System.out.println("Indtast din fødselsdag: ");
            int date = scan.nextInt();
            System.out.println("Indtast din fødsels måned: ");
            int month = scan.nextInt();
            System.out.println("Indtast dit fødsels år: ");
            int year = scan.nextInt();
            System.out.println("Er du konkurrence svømmer?: ");
            String competitionSwimmer = scan.nextLine();
            boolean isCompetitionSwimmer;

            while (!competitionSwimmer.equals("ja") && !competitionSwimmer.equals("nej")) {
                System.out.println("Du skal indtaste ja eller nej");
                competitionSwimmer = scan.nextLine();
            }
            if (competitionSwimmer.equals("ja")) {
                isCompetitionSwimmer = true;
            } else {
                isCompetitionSwimmer = false;
            }

            System.out.println(firstName + lastName + date + month + year + competitionSwimmer);
        } catch (Exception e) {
            System.out.println("Der gik noget galt med indtastningen af oplysninger");
        }
    }

    public static void editMember() {
        try {
            System.out.println("Her er starten på metoden til at redigere medlemmets oplysninger");
            String searchTerm = scan.next();
            scan.nextLine();
            ArrayList<Member> searchResult = new ArrayList<>();
            for (Member member : database.findMemberByName(searchTerm)) { //TODO det giver ingen mening, da findMember by name allerede returnerer en liste
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

    public static void deleteMember() {
        System.out.println("Her skal man kunne fjerne et medlem");
    }

    public static void viewMemberList() {
        System.out.println("Her skal man kunne se oversigten af medlemmer");
    }
}


