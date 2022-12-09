package UI;

import Main.Controller;
import Main.Database;
import Program.*;
import Sort.Sort;

import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private final String CLUB_NAME = "Svømmeklub Delfinen";
    private Controller controller = new Controller();
    Scanner scan = new Scanner(System.in);

    FileHandler fileHandler = new FileHandler();

    Database database = new Database();

    private Sort sort = new Sort();

    public void start() throws FileNotFoundException {
        try {
            controller.load();

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
                case MEMBER -> memberMenu(access.getMember());
                case TRAINER -> trainerMenu(access.getMember());
                case CASHIER -> cashierMenu(access.getMember());
                case CHAIRMAN -> chairmanMenu(access.getMember());
            }
        }while (true);
    }

    public Access loginUser(){
        System.out.println("Dette er login menu for Delfinens medlemmer");
        System.out.println("Indtast dit brugernavn: ");
        String userName = scan.nextLine();
        System.out.println("Indtast din kode:");
        String passWord = scan.nextLine();
        return controller.login(userName,passWord);
    }
    public void chairmanMenuText(Member member) {
        System.out.println("Velkommen "+member.getFirstName()+" "+member.getLastName()+"\n" +
                "Som formand af "+CLUB_NAME+" har du følgende valgmulighede" + "-".repeat(35) +
                "\n 1. Tilføj medlem " +
                "\n 2. Rediger medlem " +
                "\n 3. Slet medlem " +
                "\n 4. Se medlemsliste " +
                "\n 6. Søg efter medlem " +
                "\n 5. Save " +
                "\n 7. Sorter liste " +
                "\n 9. Din profil" +
                "\n10. Log ud"+
                "\n 0. Afslut");
    }
    public void chairmanMenu(Member member) throws FileNotFoundException {
        boolean isRunning = true;
        while (isRunning) {
            chairmanMenuText(member);
            int command =  returnInt() ;

            switch (command) {
                case 1 ->
                    addMember();
                case 2 -> controller.editMember();
                case 3 -> controller.deleteMember();
                case 4 -> viewMemberList();
                case 5 -> fileHandler.save(database.getMemberList());
                case 6 -> System.out.println(search());
                case 7 -> sortMenu();
                case 9 -> dinProfil(member);
                case 10-> isRunning = false;
                case 0 -> System.exit(0);



                default -> invalidInput();
            }

        }

    }
    public void cashierMenuText(Member member) {
        System.out.println("Velkommen "+member.getFirstName()+" "+member.getLastName()+"\n" +
                "Som kasser af "+CLUB_NAME+" har du følgende valgmulighede" + "-".repeat(35) +
                "\n 1. Se prisliste" +
                "\n 2. Restance " +
                "\n 3. Samlede indtægt " +
                "\n 4. Se på et medlem " +

                "\n 6. Din profil" +
                "\n10. Log ud"+
                "\n 0. Afslut");
    }


    public void cashierMenu(Member herSelf){
        boolean isRunning = true;
        controller.loadAllpaymentFiles();
        controller.updatePaymentsAndSave();
        while (isRunning) {
            cashierMenuText(herSelf);
            int command = returnInt();
            switch (command) {
                case 1 -> priceList();
                case 2 -> sort.sortByPayed(controller.getMemberList()); // viser intet
                case 3 -> System.out.println("Den totale indkomst fra kontingenter er "+controller.getTotalPayment()+"kr");
                case 4 -> lookAtMember(search());

                case 6 -> dinProfil(herSelf);
                case 10-> isRunning = false;
                case 0 -> System.exit(0);
                default -> invalidInput();
            }
        }
    }



    public void trainerMenuText(Member himSelf) {
        System.out.println("Velkommen "+himSelf.getFirstName()+" "+himSelf.getLastName()+"\n" +
                "Som træner af "+CLUB_NAME+" har du følgende valgmulighede" + "-".repeat(35) +
                "\n 1. Top 5 " +
                "\n 2. Se resultater " +
                "\n 3. Ret resultater " +
                "\n 4. Registring til konkurrencer " +
                "\n 5. Tilføj nyt resultat" +
                "\n 9. Din profil" +
                "\n10. Log ud"+
                "\n 0. Afslut");
    }
    public void trainerMenu(Member himSelf){
        boolean isRunning = true;
        while (isRunning) {
            trainerMenuText(himSelf);
            int command = returnInt();

            switch (command) {
                case 1 -> System.out.println("fff");
                case 2 -> viewMemberResults();
                case 3 -> System.out.println("ggg");
                case 4 -> System.out.println("ooo");
                case 5 -> addResult(search());
                case 9 -> dinProfil(himSelf);
                case 10-> isRunning = false;
                case 0 -> System.exit(0);
                default -> invalidInput();
            }

        }

    }

    private void addResult(Member swimmer) {
        if(swimmer instanceof CompeteSwimmer){
            System.out.println("Tilføjer resultater for: "+swimmer.getFirstName());
            System.out.println("Vælg deciplin:");
            boolean pass = true;
            Discipline discipline = null;
            double time;
            int distance;
            String competitionName;
            do {
                System.out.println("\n1. Crawl\n2. Rygcrawl\n3. Butterfly");
                int choice = returnInt();
                switch (choice){
                    case 1 -> discipline = Discipline.CRAWL;
                    case 2 -> discipline = Discipline.BACKSTROKE;
                    case 3 -> discipline = Discipline.BUTTERFLY;
                    default -> pass = false;
                }
            }while (!pass);
            System.out.println("Indtast distance i antal 100m");
            distance = returnInt();
            System.out.println("Indtast tid i sekunder");
            time = returnDouble();
            System.out.println("Indtast navn på konkurence");
            competitionName = scan.nextLine();
            controller.addResult(swimmer,discipline,time,distance,competitionName);
            System.out.println(swimmer.getFirstName()+"s resultat er tilføjet");
            controller.saveResult(swimmer);
        }else{
            System.out.println(swimmer.getFirstName()+" er ikke en konkurencesvømmer");
        }
    }

    public void memberMenuText(Member member){
        System.out.println("Velkommen "+member.getFirstName()+" "+member.getLastName()+"\n" +
                "Som medlem af "+CLUB_NAME+" har du følgende valgmulighede" + "-".repeat(35) +
                "\n 9. Din profil" +
                "\n10. Log ud"+
                "\n 0. Afslut");
    }


    public void memberMenu(Member member){
        scan.nextLine();
        boolean isRunning = true;
        while (isRunning) {
            memberMenuText(member);
            int command = returnInt();

            switch (command) {

                case 9 -> dinProfil(member);
                case 10-> isRunning = false;
                case 0 -> System.exit(0);
                default -> invalidInput();
            }
        }
    }

    private void dinProfil(Member you) {
        boolean run = true;
        do {
            System.out.println("\n1. Se profildata\n2. Ret brugernavn og kodeord\n3. Betal for medlemsskab\n0. Vend tilbage til hovedmenu ");
            switch (returnInt()){
                case 1 -> System.out.println(you);
                case 2 -> {
                    boolean pass = false;
                    do {
                        System.out.println("Indtast nyt brugernavn");
                        String newUseName = scan.nextLine();
                        if (controller.evalUsername(newUseName)){ //method that tells if the desired username is vacant
                            you.setUsername(newUseName);
                            pass = true;
                        }else {
                            System.out.println("username already exist");
                        }

                    }while (!pass);
                    System.out.println("Indtast nyt kodeord");
                    you.setPassword(scan.nextLine());
                    try {
                        controller.save();
                        System.out.println("Du kan nu bruge dit nye login, Brugernav: "+you.getUsername()+" Kode: "+
                                you.getPassword());
                    }catch (FileNotFoundException e){
                        System.out.println("fej kunne ikke gemme endring");
                    }

                }
                case 3 -> {
                    controller.lodPayment(you);
                    double balance = you.getBallance();
                    if (balance<=0) {
                        System.out.println("Du skylder " + -balance + "kr.");

                    }else {
                        System.out.println("Du har " + balance + "kr i overskud");
                    }
                    System.out.println("Indtast beløb du ønsker at betale");
                    double amount = returnDouble();
                    if(amount>0) {
                        you.payment(amount);
                        controller.savePayment(you);
                    }
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
        for(Member member: controller.getMemberList()){
            if(member instanceof CompeteSwimmer) {
                System.out.println("\n" + "-".repeat(30) + "\n" + member.getFirstName() + " " + member.getLastName() + "\n");
                if(!((CompeteSwimmer)member).getResultList().isEmpty()) {
                    for (Results results : controller.getResultList(member)) {
                        System.out.println(results);
                    }
                }else {
                    System.out.println("Har ingen resultater");
                }
            }
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

        sort.sortByMembership(controller.getMemberList());// sikre at der bliver sorteret efter medlemsnummer.
        System.out.println();
        System.out.println("Indtast fornavn: ");
        String firstName = scan.nextLine();
        //scan.nextLine();
        System.out.println("Indtast efternavn: ");
        String lastName = scan.nextLine();
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

        }while(dateOfBirth==null);
        System.out.println("Er det et aktivt meslemskab:");
        boolean active = returnBoolean();
        System.out.println("Er personen konkurrence svømmer?: ");
        boolean isCompetitionSwimmer = returnBoolean();
        System.out.println("Er det en bruger med adminitrativ adgang?");
        Users userType;
        if(returnBoolean()) {
            System.out.println("Vælg Medlemstype:\nTast m for medlem\nTast t for træner\nTast k for kasser\nTast f for Formand");
            userType = returnUserType();
        }else{
            userType = Users.MEMBER;
        }

        controller.addMember(firstName,lastName,dateOfBirth,active,isCompetitionSwimmer,userType);

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
    private double returnDouble() {
        boolean pass = false;
        double returnDouble = 0;
        do {
            try {
                returnDouble = Integer.parseInt(scan.nextLine());
                pass = true;
            } catch (NumberFormatException e) {
                if(!pass) {
                    System.out.println("fejl! indtast et tal");
                }
            }
        }while (!pass);
        return returnDouble;
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
                    i++;
                }
                memberChoice = searchResult.get(returnInt()-1);
            }else {
                memberChoice = searchResult.get(0);
            }
        }
        return memberChoice;
    }
    private void lookAtMember(Member memberToLookAt) {
        boolean run = true;
        int choice;
        System.out.println("Looking at: "+memberToLookAt.getFirstName()+" "+memberToLookAt.getLastName());
        while (run) {
            System.out.println("\n1. info\n2. se kontoudtog\n0. Tilbage til hovedmenu ");
            choice = returnInt();
            switch (choice) {
                case 1 -> System.out.println(memberToLookAt);
                case 2 -> System.out.println(accountStatement(memberToLookAt));
                case 0 -> run = false;
                default -> invalidInput();
            }
        }
    }
    private String accountStatement(Member memberToLookAt) {
        System.out.println("Account statement for "+ memberToLookAt.getFirstName()+" "+memberToLookAt.getLastName());
        String accountStatement ="Dato          Kontingent     Betaling\n";
        double sum = 0;
        for(AccountTransaction transaction: memberToLookAt.getPaymentList()){
            accountStatement += transaction.getDate()+" "+transaction.getSubscription()+" ".repeat(10)+transaction.getPayment()+"\n";
            sum += - transaction.getSubscription() + transaction.getPayment();
        }
        accountStatement += "Saldo er: "+sum;
        return accountStatement;
    }

}