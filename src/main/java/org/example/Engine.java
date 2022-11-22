package org.example;

import java.util.Scanner;

public class Engine {
    Scanner scan = new Scanner(System.in);

    public void startMenu() {
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

            }*/
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
            String surName = scan.next();
            scan.nextLine();
            String lastName = scan.next();
            int date = scan.nextInt();
            int month = scan.nextInt();
            int year = scan.nextInt();
            boolean competitionSwimmer = scan.hasNextBoolean();
            System.out.println(surName + lastName + date + month + year + competitionSwimmer);
        } catch (Exception e) {
            System.out.println("There was a typo in the information");
        }
    }

}
