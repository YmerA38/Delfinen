package org.example;

public class UI {

  public void startMenu(){
    System.out.println("Velkommen\n" + "-".repeat(35) +
            "\n1. Tilføj medlem " +
            "\n2. Rediger medlem " +
            "\n3. Slet medlem " +
            "\n4. Se medlemmer " +
            "\n0. Afslut");
  }

public void invalidInput() {
  System.out.println("Ugyldigt input. Prøv igen!");
}

}
