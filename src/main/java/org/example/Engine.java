package org.example;

import java.util.Scanner;

public class Engine {
    Scanner scan = new Scanner(System.in);

    UI ui = new UI();

    public void runProgram() {
      /*  boolean isRunning = true;
        while (isRunning){
            String command = scan.nextLine();

            switch (command) {
                case "1" -> addMember();
                case "2" ->
                case "3" ->
                case "4" ->
                case "5" ->
                case "6" ->
                case "7" ->
                case "8" ->
                case "9" ->
                case "0" ->

            }
            default -> ui.invalidInput
       */
        int menu;
        menu = scan.nextInt();
        scan.nextLine();
        if (menu == 1) {
            addMember();
        }
    }

    private void addMember() {
        try {
            System.out.println();
            System.out.println("Indtast dit fornavn: ");
            String surName = scan.next();
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
            boolean competitionSwimmer = scan.hasNextBoolean();
            System.out.println(surName + lastName + date + month + year + competitionSwimmer);
        } catch (Exception e) {
            System.out.println("Der gik noget galt med indtastningen af oplysninger");
        }
    }

}
