package UI;

import Main.Controller;
import Main.Database;
import Program.*;
import Sort.Sort;

import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {
    private final String CLUB_NAME = "Svømmeklub Delfinen";
    private Controller controller = new Controller();
    Scanner scan = new Scanner(System.in);

    private Sort sort = new Sort();

    public void start() {
        try {
            controller.load();
            //viewMemberList();

            System.out.println("Fil indlæst");
        }catch (FileNotFoundException e){
            System.out.println("Manglende datafil fejl!! ");
        }

        System.out.println("Velkommen til "+CLUB_NAME);


        Access access;
        do {
            access = loginUser();
            switch (access.getUserType()){
                case NO_USER -> System.out.println("User does not exist");
                case WRONG_PASSWORD -> System.out.println("The password is incorrect");
                case MEMBER -> runMember(access.getMember());
                case TRAINER -> runTrainer(access.getMember());
                case CASHIER -> runCashier(access.getMember());
                case CHAIRMAN -> runChairman(access.getMember());
            }

        }while (access.getUserType()==Users.NO_USER||access.getUserType()==Users.WRONG_PASSWORD);

/*      boolean menuError;
        do {
            do {
                startPage();
                try {
                    int menuChoice = returnInt();
                    if (menuChoice == 1)
                        runChiarman();
                    else if (menuChoice == 2) {
                        runKasser();
                    }else if (menuChoice == 3) {
                        runTræner();
                    }
                    menuError = false;
                } catch (InputMismatchException ime) {
                    System.out.println("Skriv kun tal");
                    scan.nextLine();
                    menuError = true;
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } while (menuError == true);
        } while (true);
        */
    }

    public void startPage() {
        System.out.println("""
                ┌──────────────────────┐	               
                │ Tast 1) Formand      │  
                ├──────────────────────┤
                │ Tast 2) Kasserer     │
                ├──────────────────────┤
                │ Tast 3) Træner       │
                ├──────────────────────┤
                │ Tast 4) Medlem       │
                └──────────────────────┘""");


    }
    public Access loginUser(){
        System.out.println("Dette er login menu for Delfinens medlemmer");
        System.out.println("Indtast dit brugernavn: ");
        String userName = scan.nextLine();
        System.out.println("Indtast din kode:");
        String passWord = scan.nextLine();
        return controller.login(userName,passWord);
    }
    public void chairmanMenu(Member member) {
        System.out.println("Velkommen "+member.getFirstName()+" "+member.getLastName()+"\n" +
                "Som formand af "+CLUB_NAME+" har du følgende valgmulighede" + "-".repeat(35) +
                "\n1. Tilføj medlem " +
                "\n2. Rediger medlem " +
                "\n3. Slet medlem " +
                "\n4. Se medlemmer " +
                "\n5. Save " +

                "\n7. Sorter liste " +
                "\n8. Søg efter medlem " +
                "\n9. Din profil" +
                "\n0. Afslut");
    }
    public void runChairman(Member member){
        boolean isRunning = true;
        while (isRunning) {
            chairmanMenu(member);
            int command =  returnInt() ;

            switch (command) {
                case 1 -> {addMember();
                    try{// ønsker at gemme med det samme
                        controller.save();
                    }catch (FileNotFoundException e){
                        System.out.println("file not found error");
                    }
                }
                case 2 -> controller.editMember();
                case 3 -> controller.deleteMember();
                case 4 -> System.out.println(search());
                case 5 -> viewMemberList();
                case 7 -> sortMenu();
                case 8 -> search();
                case 9 -> dinProfil(member);
                case 0 -> System.exit(0);

            /*    case "7" ->
                case "8" ->
                case "9" ->
                case "0" ->
*/


                default -> invalidInput();
            }

        }

    }
    public void cashierMenu(Member member) {
        System.out.println("Velkommen "+member.getFirstName()+" "+member.getLastName()+"\n" +
                "Som kasser af "+CLUB_NAME+" har du følgende valgmulighede" + "-".repeat(35) +
                "\n1. Se prisliste" +
                "\n2. Restance " +
                "\n3. Samlede indtægt " +
                "\n4. Søg efter medlem " +
                "\n8. indtægt " +
                "\n9. Din profil" +
                "\n0. Afslut");
    }


    public void runCashier(Member member){
        scan.nextLine();
        boolean isRunning = true;
        while (isRunning) {
            cashierMenu(member);
            int command = returnInt();

            switch (command) {
                case 1 -> priceList();
                case 2 -> sort.sortByPayed(controller.getMemberList());
                case 3 -> System.out.println("in progess");//controller.getTotalPayment();
                case 4 -> System.out.println(search());
                case 9 -> dinProfil(member);
                case 0 -> System.exit(0);
                default -> invalidInput();
            }

        }

    }
    public void trainerMenu(Member member) {
        System.out.println("Velkommen "+member.getFirstName()+" "+member.getLastName()+"\n" +
                "Som træner af "+CLUB_NAME+" har du følgende valgmulighede" + "-".repeat(35) +
                "\n1. Top 5 " +
                "\n2. Se resultater " +
                "\n3. Ret resultater " +
                "\n4. Registring til konkurrencer " +
                "\n9. Din profil" +
                "\n0. Afslut");
    }
    public void runTrainer(Member member){
        scan.nextLine();
        boolean isRunning = true;
        while (isRunning) {
            trainerMenu(member);
            int command = returnInt();

            switch (command) {
                case 1 -> System.out.println("fff");
                case 2 -> viewMemberResults();
                case 3 -> System.out.println("ggg");
                case 4 -> System.exit(0);
                case 9 -> dinProfil(member);
                default -> invalidInput();
            }

        }

    }
    public void memberMenu(Member member){
        System.out.println("Velkommen "+member.getFirstName()+" "+member.getLastName()+"\n" +
                "Som medlem af "+CLUB_NAME+" har du følgende valgmulighede" + "-".repeat(35) +
                "\n9. Din profil" +
                "\n0. Afslut");
    }


    public void runMember(Member member){
        scan.nextLine();
        boolean isRunning = true;
        while (isRunning) {
            memberMenu(member);
            int command = returnInt();

            switch (command) {
                case 1 -> System.out.println("kk");
                case 2 -> System.out.println("fff");
                case 3 -> System.out.println("ggg");
                case 4 -> System.exit(0);
                case 9 -> dinProfil(member);
                default -> invalidInput();
            }
        }
    }

    private void dinProfil(Member member) {
        boolean run = true;
        do {
            System.out.println("\n1. Se profildata\n2. Ret brugernavn og kodeord\n0. Vend tilbage til hovedmenu ");
            switch (returnInt()){
                case 1 -> System.out.println(member);
                case 2 -> {
                    System.out.println("Indtast nyt brugernavn" );
                    member.setPassword(scan.nextLine());
                    System.out.println("Indtast nyt kodeord");
                    member.setUsername(scan.nextLine());
                }
                case 0 -> run = false;
                default -> System.out.println("type 1, 2 or 0");
            }

        }while(run);
    }



    private void viewMemberList() {
        for(Member member: controller.getMemberList()){
            System.out.println(member);
        }
    }




    private void viewMemberResults() {
            for (Results member : controller.getResultList()) {
                System.out.println(member.resultList);
            }
    }




    public void sortMenuPrint(){
        System.out.println("Du kan nu vælge at sortere medlemslisten efter følgende:\n1: Fornavn\n2:Efternavn" +
                "\n3: Alder\n4: Aktivitetsniveau\n5: Konkurrencesvømmer\n6: Betalt/Ikke betalt" +
                "\n7: Dato for medlemsskab\n8: Medlemsnummer\n9: Hold\n10: Medlemstype\n ");
    }
    public void sortMenu (){
        boolean run = true;
        while (run) {
            run = false;
            sortMenuPrint();
            switch (scan.nextLine()) {
                case "1" -> sort.sortByFirstname(controller.getMemberList());
                case "2" -> sort.sortByLastname(controller.getMemberList());
                case "3" -> sort.sortByAge(controller.getMemberList());
                case "4" -> sort.sortByActive(controller.getMemberList());
                case "5" -> sort.sortByCompetitive(controller.getMemberList());
                case "6" -> sort.sortByPayed(controller.getMemberList());
                case "7" -> sort.sortByDateOfMembership(controller.getMemberList());
                case "8" -> sort.sortByMembership(controller.getMemberList());
                case "9" -> sort.sortByTeam(controller.getMemberList());
                case "10" -> sort.sortByUsertype(controller.getMemberList());
                default -> {
                    invalidInput();
                    run = true;
                }
            }
        }
        viewMemberList();
    }

    public void addMember() {

        System.out.println();
        System.out.println("Indtast fornavn: ");
        String firstName = scan.next();
        scan.nextLine();
        System.out.println("Indtast efternavn: ");
        String lastName = scan.next();
        LocalDate dateOfBirth;
        do {
            System.out.println("Fødselsdag: ");
            System.out.println("Indtast dag:");
            int day = returnInt();
            System.out.println("Indtast fødsels måned: ");
            int month = returnInt();
            System.out.println("Indtast fødsels år: ");
            int year = returnInt();
            dateOfBirth = returnDate(day,month,year);
            System.out.println(day+" "+month+" "+year);
        }while(dateOfBirth==null);
        System.out.println("Er det et aktivt meslemskab:");
        boolean active = returnBoolean();
        System.out.println("Er personen konkurrence svømmer?: ");
        boolean isCompetitionSwimmer = returnBoolean();
        System.out.println("Har personen betalt medlemsskab?");
        boolean hasPayed = returnBoolean();
        System.out.println("Vælg Medlemstype:\nTast m for medlem\nTast t for træner\nTast k for kasser\nTast f for Formand");
        Users userType = returnUserType();

        controller.addMember(new Member(firstName,lastName,dateOfBirth,active,isCompetitionSwimmer,hasPayed,userType));
    }

    private Users returnUserType() {
        char choice;
        do {
            choice = scan.nextLine().charAt(0);
            switch (choice){
                case 'm'-> {return Users.MEMBER;}
                case 't' -> {return Users.TRAINER;}
                case 'k' -> {return Users.CASHIER;}
                case 'f' -> {return Users.CHAIRMAN;}
            }
        }while(true);
    }








    public void invalidInput() {
        System.out.println("Ugyldigt input. Prøv igen!");
    }

    public int returnInt(){
        boolean pass = false;
        int returnInt = 0;
        do {
            try {
                returnInt = Integer.parseInt(scan.nextLine());
                pass = true;
            } catch (NumberFormatException e) {
                System.out.println("fejl! indtast et tal");
            }
        }while (!pass);
        return returnInt;
    }
    public boolean returnBoolean(){
        String string;
        do {
            System.out.println("Du skal indtaste ja eller nej");
            string = scan.nextLine();
        }while (!string.equals("ja") && !string.equals("nej"));
        if (string.equals("ja")) {
            return true;
        } else {
            return false;
        }

    }


    public LocalDate returnDate(int day,int month,int year){
        try{
            return LocalDate.of(year,month,day);
        }catch (DateTimeException e){
            System.out.println("fejl i dato indtastning");
            return null;
        }
    }

    public void priceList(){
        System.out.println("Junior (under 18): 1000 kr." + "\nSenior (18 år og over): 1600 kr."
        + "\nMedlemmer over 60: 1200 kr." + "\nPassivt medlemskab: 500 kr.");
    }

    public Member search(){
        System.out.println("Indtast navn på det medlem du ønsker at finde");
        String searchTerm = scan.nextLine();
        ArrayList<Member> searchResult = controller.findMemberByName(searchTerm);
        Member memberChoice = null;
        if(searchResult.isEmpty()){
            System.out.println("ingen medlemmer fundet");

        }else {
            System.out.println("Medlemmer fundet");
            if(searchResult.size()>1) {
                System.out.println("Vælg medlem fra liste");
                int i = 1;
                for (Member member : searchResult) {
                    System.out.println(i+": "+member.getFirstName()+" "+member.getLastName());
                }
                memberChoice = searchResult.get(returnInt()-1);
            }else {
                memberChoice = searchResult.get(0);
            }
        }
        return memberChoice;
    }

   /* public boolean catchFileException(FileHandler fileHandler){
        {
            try{
                fileHandler
            }catch (FileNotFoundException e){
                System.out.println("file not found error");
            }
        }
    }*/

}