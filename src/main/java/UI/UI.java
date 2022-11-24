package UI;

import Main.Controller;
import Main.Database;
import Program.FileHandler;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class UI {
  private Controller engine = new Controller();
  FileHandler fileHandler = new FileHandler();
  Database database;
  Scanner scan = new Scanner(System.in);


  public void runProgram() throws FileNotFoundException {
    scan.nextLine();
    boolean isRunning = true;
    while (isRunning) {
      startMenu();
      String command = scan.nextLine();

      switch (command) {
        case "1" -> Controller.addMember();
        case "2" -> Controller.editMember();
        case "3" -> Controller.deleteMember();
        case "4" -> Controller.viewMemberList();
        case "5" -> Controller.fileHandler.load();
        case "6" -> Controller.fileHandler.save(database.getMemberList());
                /*
                case "7" ->
                case "8" ->
                case "9" ->
                case "0" ->

                 */


        default -> invalidInput();
      }


    }


  }

  public void startMenu(){
    System.out.println("Velkommen\n" + "-".repeat(35) +
            "\n1. Tilføj medlem " +
            "\n2. Rediger medlem " +
            "\n3. Slet medlem " +
            "\n4. Se medlemmer " +
            "\n5. Save " +
            "\n4. Load " +
            "\n0. Afslut");
    }

public void invalidInput() {
  System.out.println("Ugyldigt input. Prøv igen!");
}

}
