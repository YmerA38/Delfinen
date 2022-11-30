package UI;

import Main.Controller;
import Main.Database;
import Program.Access;
import Program.FileHandler;
import Program.Member;
import Program.Users;
import Program.Subscription;

import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {
    private final String CLUB_NAME = "Svømmeklub Delfinen";
    private Controller controller = new Controller();
    FileHandler fileHandler = new FileHandler();
    Database database = new Database();

    Scanner scan = new Scanner(System.in);



    public void start() {
        System.out.println("Velkommen til Delfin svømmeklub");


     /*   Access access;
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

        }while (access.getUserType()==Users.NO_USER||access.getUserType()==Users.WRONG_PASSWORD);*/

    boolean menuError;
        do {
            do {
                startPage();
                try {
                    int menuChoice = returnInt();
                    if (menuChoice == 1)
                        runChairman();
                    else if (menuChoice == 2) {
                        runCashier();
                    }else if (menuChoice == 3) {
                        runTrainer();
                    }
                    menuError = false;
                } catch (InputMismatchException ime) {
                    System.out.println("Skriv kun tal");
                    scan.nextLine();
                    menuError = true;
                }
            } while (menuError == true);
        } while (true);

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

    public void runChairman(){
        System.out.println("Velkommen");
        scan.nextLine();
        boolean isRunning = true;
        while (isRunning) {
            chairmanMenu();
            int command =  returnInt() ;

            switch (command) {
                case 1 -> addMember();
                case 2 -> controller.editMember();
                case 3 -> controller.deleteMember();
                case 4 -> viewMemberList();
                case 5 -> {
                    try{
                    controller.load();
                    }catch (FileNotFoundException e){
                        System.out.println("file not found error");
                    }
                }
                case 6 ->{
                    try{
                        controller.save();
                    }catch (FileNotFoundException e){
                        System.out.println("file not found error");
                    }

                }
                case 0 -> System.exit(0);

                default -> invalidInput();
            }

        }

    }
    public void chairmanMenu() {
        System.out.println("Velkommen" +
                "Som formand af "+CLUB_NAME+" har du følgende valgmulighede" + "-".repeat(35) +
                "\n1. Tilføj medlem " +
                "\n2. Rediger medlem " +
                "\n3. Slet medlem " +
                "\n4. Se medlemmer " +
                "\n5. Save " +
                "\n6. Load " +
                "\n9. Din profil" +
                "\n0. Afslut");
    }

    private void viewMemberList() {
        for(Member member: controller.getMemberList()){
            System.out.println(member);
        }
    }

    public void runCashier(){
        scan.nextLine();
        boolean isRunning = true;
        while (isRunning) {
            cashierMenu();
            String command = scan.nextLine();

            switch (command) {
                case "1" -> System.out.println(Subscription.checkIncomeEstimate());
                case "2" -> System.out.println("ggg");
                case "3" -> System.out.println("gggg");
                case "0" -> System.exit(0);


                default -> invalidInput();
            }

        }

    }

    public void runTrainer(){
        scan.nextLine();
        boolean isRunning = true;
        while (isRunning) {
            trainerMenu();
            String command = scan.nextLine();

            switch (command) {
                case "1" -> System.out.println("kk");
                case "2" -> System.out.println("fff");
                case "3" -> System.out.println("ggg");
                case "0" -> System.exit(0);


                default -> invalidInput();
            }

        }

    }
    public void runMember(Member member){
        scan.nextLine();
        boolean isRunning = true;
        while (isRunning) {
            memberMenu();
            String command = scan.nextLine();

            switch (command) {
                case "1" -> System.out.println();
                case "2" -> System.out.println("fff");
                case "3" -> System.out.println("ggg");
                case "0" -> System.exit(0);

                default -> invalidInput();
            }

        }

    }
    public void memberMenu(){
        System.out.println("Velkommen "+"\n" +
                "Som medlem af "+CLUB_NAME+" har du følgende valgmulighede" + "-".repeat(35) +
                "\n9. Din profil" +
                "\n0. Afslut");
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
        System.out.println("Er du konkurrence svømmer?: ");
        boolean isCompetitionSwimmer = returnBoolean();
        System.out.println("Vælg Medlemstype:\nTast m for medlem\nTast t for træner\nTast k for kasser\nTast f for Formand");
        Users userType = returnUserType();

        controller.addMember(new Member(firstName,lastName,dateOfBirth,active,isCompetitionSwimmer,userType));

    }

    private Users returnUserType() {
        char choice;
        do {
            choice = scan.nextLine().charAt(0);
            switch (choice){
                case 'm'-> {return Users.CHAIRMAN;}
                case 't' -> {return Users.TRAINER;}
                case 'k' -> {return Users.CASHIER;}
                case 'f' -> {return Users.CHAIRMAN;}
            }
        }while(true);
    }




    public void cashierMenu() {
        System.out.println("Velkommen " + "\n" +
                "Som kasser af "+CLUB_NAME+" har du følgende valgmulighede" + "-".repeat(35) +
                "\n1. Se indbetalinger " +
                "\n2. Restance " +
                "\n3. Samlede indtægt " +
                "\n4. Søg efter medlem " +
                "\n9. Din profil" +
                "\n0. Afslut");
    }

    public void trainerMenu() {
        System.out.println("Velkommen "+"\n" +
                "Som træner af "+CLUB_NAME+" har du følgende valgmulighede" + "-".repeat(35) +
                "\n1. Top 5 " +
                "\n2. Se resultater " +
                "\n3. Ret resultater " +
                "\n4. Registring til konkurrencer " +
                "\n9. Din profil" +
                "\n0. Afslut");
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

}