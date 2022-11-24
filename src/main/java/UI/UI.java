package UI;

import Main.Controller;
import Main.Database;
import Program.FileHandler;
import Program.Member;
import Program.Users;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {
    private Controller controller = new Controller();
    FileHandler fileHandler = new FileHandler();
    Database database;
    Scanner scan = new Scanner(System.in);

    public void start() {
        System.out.println("Velkommen til Delfin svømmeklub");



        boolean menuError;

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
    public Users loginUser(){
        System.out.println("Dette er login menu for Delfinens medlemmer");
        System.out.println("Indtast dit brugernavn: ");
        String userName = scan.nextLine();
        System.out.println("Indtast din kode:");
        String passWord = scan.nextLine();
        return controller.login(userName,passWord);
    }

    public void runChiarman(){
        scan.nextLine();
        boolean isRunning = true;
        while (isRunning) {
            formandMenu();
            int command =  returnInt() ;

            switch (command) {
                case 1 -> addMember();
                case 2 -> controller.editMember();
                case 3 -> controller.deleteMember();
                case 4 -> controller.viewMemberList();
                case 5 -> {
                    try{
                    controller.fileHandler.load();
                    }catch (FileNotFoundException e){
                        System.out.println("file not found error");
                    }
                }
                case 6 ->{
                    try{
                        controller.fileHandler.save(database.getMemberList());
                    }catch (FileNotFoundException e){
                        System.out.println("file not found error");
                    }
                }
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

    public void runKasser() throws FileNotFoundException {
        scan.nextLine();
        boolean isRunning = true;
        while (isRunning) {
            kasserMenu();
            String command = scan.nextLine();

            switch (command) {
                case "1" -> System.out.println("fff");
                case "2" -> System.out.println("ggg");
                case "3" -> System.out.println("gggg");
                case "0" -> System.exit(0);

            /*    case "7" ->
                case "8" ->
                case "9" ->
                case "0" ->
*/


                default -> invalidInput();
            }

        }

    }

    public void runTræner() throws FileNotFoundException {
        scan.nextLine();
        boolean isRunning = true;
        while (isRunning) {
            trænerMenu();
            String command = scan.nextLine();

            switch (command) {
                case "1" -> System.out.println("kk");
                case "2" -> System.out.println("fff");
                case "3" -> System.out.println("ggg");
                case "0" -> System.exit(0);
            /*    case "7" ->
                case "8" ->
                case "9" ->

*/


                default -> invalidInput();
            }

        }

    }
    public void addMember() {

        System.out.println();
        System.out.println("Indtast fornavn: ");
        String firstName = scan.next();
        scan.nextLine();
        System.out.println("Indtast efternavn: ");
        String lastName = scan.next();
        System.out.println("Fødselsdag: ");
        System.out.println("Indtast dag:");
        int day = returnInt();
        System.out.println("Indtast fødsels måned: ");
        int month = returnInt();
        System.out.println("Indtast fødsels år: ");
        int year = returnInt();
        System.out.println("Er det et aktivt meslemskab:");
        boolean active = returnBoolean();
        System.out.println("Er du konkurrence svømmer?: ");
        boolean isCompetitionSwimmer = returnBoolean();
        System.out.println("Vælg Medlemstype:\nTast m for medlem\n Tast t for træner\nTast k for kasser\n Tast f for Formand");
        Users userType = returnUserType();

        controller.addMember(new Member(firstName,lastName, LocalDate.of(year,month,year),active,isCompetitionSwimmer,userType));

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


    public void formandMenu() {
        System.out.println("Velkommen\n" + "-".repeat(35) +
                "\n1. Tilføj medlem " +
                "\n2. Rediger medlem " +
                "\n3. Slet medlem " +
                "\n4. Se medlemmer " +
                "\n5. Save " +
                "\n4. Load " +
                "\n0. Afslut");
    }

    public void kasserMenu() {
        System.out.println("Velkommen\n" + "-".repeat(35) +
                "\n1. Se indbetalinger " +
                "\n2. Restance " +
                "\n3. Samlede indtægt " +
                "\n4. Søg efter medlem " +
                "\n0. Afslut");
    }

    public void trænerMenu() {
        System.out.println("Velkommen\n" + "-".repeat(35) +
                "\n1. Top 5 " +
                "\n2. Se resultater " +
                "\n3. Ret resultater " +
                "\n4. Registring til konkurrencer " +
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