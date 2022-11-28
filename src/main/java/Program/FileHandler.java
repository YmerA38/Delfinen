package Program;

import Main.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private Database database;

    private final String FILE_SAVE = "data/memberDatabase.csv";
    public boolean load() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File(FILE_SAVE));
        String line;
        String[] entity;
        String[] date1;
        String[] date2;

        do{
            line = fileScanner.nextLine();
            if(!line.isEmpty()){
                entity = line.split(",");
                date1 = entity[2].split("/");
                date2 = entity[6].split("/");
                database.addMember(new Member(entity[0],entity[1], LocalDate.of(Integer.parseInt(date1[2]),
                        Integer.parseInt(date1[1]),Integer.parseInt(date1[0])),
                        Boolean.parseBoolean(entity[3]),Boolean.parseBoolean(entity[4]),
                        Boolean.parseBoolean(entity[5]),LocalDate.of(Integer.parseInt(date2[2]),
                        Integer.parseInt(date2[1]),Integer.parseInt(date2[0])),Integer.parseInt(entity[7]),
                        Team.valueOf(entity[8]),entity[9],entity[10],Users.valueOf(entity[11])));
            }
        }while(fileScanner.hasNextLine()&&!line.isEmpty());

        return true;
    }

    public boolean save(ArrayList<Member> memberList) throws FileNotFoundException {
        PrintStream fileSaver = new PrintStream(FILE_SAVE);

        for(Member member:memberList){
            fileSaver.println(member.getFirstName()+","+member.getFirstName()+","+member.getDateOfBirth()+","+
                    member.getIsActive()+","+member.getIsCompeting()+","+member.getHasPayed()+","+
                    member.getDateOfMembership()+","+member.getMembershipNumber()+","+member.getTeam());
        }
        fileSaver.flush();
        fileSaver.close();


        return true;
    }
}
