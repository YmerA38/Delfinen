package Program;

import java.io.FileNotFoundException;

public class UI {
  private Engine engine = new Engine();

  public void startMenu(){
    System.out.println("Velkommen\n" + "-".repeat(35) +
            "\n1. Tilføj medlem " +
            "\n2. Rediger medlem " +
            "\n3. Slet medlem " +
            "\n4. Se medlemmer " +
            "\n5. Save " +
            "\n4. Load " +
            "\n0. Afslut");
    try {
      engine.runProgram();
    }catch (FileNotFoundException e){
      System.out.println("file not found");
    }
  }

public void invalidInput() {
  System.out.println("Ugyldigt input. Prøv igen!");
}

}
