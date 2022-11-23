package org.example;

import Program.Member;
import java.util.ArrayList;
import java.util.Scanner;


public class Engine {
    Database database = new Database();
    Scanner scan = new Scanner(System.in);

    UI ui = new UI();

    public void runProgram() {
        int menu;
        menu = scan.nextInt();
        scan.nextLine();

        boolean isRunning = true;
        while (isRunning) {
            String command = scan.nextLine();

            switch (command) {
                case "1" -> addMember();
                case "2" -> editMember();
                case "3" -> deleteMember();
                case "4" -> viewMemberList();
                /*
                case "5" ->
                case "6" ->
                case "7" ->
                case "8" ->
                case "9" ->
                case "0" ->

                 */


                default -> ui.invalidInput();
            }


        }


    }

    public void addMember() {
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

    public void editMember() {
        try {


            System.out.println("Her er starten på metoden til at redigere medlemmets oplysninger");
            String searchTerm = scan.next();
            scan.nextLine();
            ArrayList<Member> searchResult = new ArrayList<>();
            for (Member member : database.findMemberByName(searchTerm)) {
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
        System.out.println("Her skal man kunne fjerne et medlem");
    }

    public void viewMemberList() {
        System.out.println("Her skal man kunne se oversigten af medlemmer");
    }
}


