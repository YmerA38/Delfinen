package UI;

import Main.Controller;
import Main.Database;
import Program.FileHandler;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {
    private Controller engine = new Controller();
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
                    int menuChoice = scan.nextInt();
                    if (menuChoice == 1)
                        runProgram();
                    else if (menuChoice == 2) {
                        runKasser();
                    } else if (menuChoice == 3) {
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
                └──────────────────────┘""");


    }

    public void runProgram() throws FileNotFoundException {
        scan.nextLine();
        boolean isRunning = true;
        while (isRunning) {
            formandMenu();
            String command = scan.nextLine();

            switch (command) {
                case "1" -> Controller.addMember();
                case "2" -> Controller.editMember();
                case "3" -> Controller.deleteMember();
                case "4" -> Controller.viewMemberList();
                case "5" -> Controller.fileHandler.load();
                case "6" -> Controller.fileHandler.save(database.getMemberList());
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

}