package Program;

import java.io.FileNotFoundException;

public class Main {
  public static void main(String[] args) {
    Engine engine = new Engine();
    try {
      engine.runProgram();
    }catch (FileNotFoundException e){
      System.out.println("file not found");
    }
  }
}