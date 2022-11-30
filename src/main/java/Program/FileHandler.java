package Program;

import Main.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {


    private final String FILE_SAVE = "data/memberDatabase.csv";
    public boolean load(Database database) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File(FILE_SAVE));
        String line;
        String[] entity;
        String[] date1;
        String[] date2;
        fileScanner.nextLine();//spring linje over
        do{
            line = fileScanner.nextLine();
            if(!line.isEmpty()){
                entity = line.split(",");
                date1 = entity[2].split("-");
                System.out.println(date1[0]+" "+date1[1]+" "+date1[2]);
                date2 = entity[6].split("-");
                System.out.println(date2[0]+" "+date2[1]+" "+date2[2]);
                database.addMember(new Member(entity[0],entity[1], LocalDate.of(Integer.parseInt(date1[0]),
                        Integer.parseInt(date1[1]),Integer.parseInt(date1[2])),
                        Boolean.parseBoolean(entity[3]),Boolean.parseBoolean(entity[4]),
                        Boolean.parseBoolean(entity[5]),LocalDate.of(Integer.parseInt(date2[0]),
                        Integer.parseInt(date2[1]),Integer.parseInt(date2[2])),Integer.parseInt(entity[7]),
                        Team.valueOf(entity[8]),entity[9],entity[10],Users.valueOf(entity[11])),false);
            }
        }while(fileScanner.hasNextLine()&&!line.isEmpty());

        return true;
    }

    public boolean save(ArrayList<Member> memberList) throws FileNotFoundException {
        PrintStream fileSaver = new PrintStream(FILE_SAVE);
        fileSaver.println("Navn,Efternavn,Fødselsdato,aktiv,konkurrence,betalt,oprettet,nr,Hold,Brugernavn,kode,Adgangstype");
        for(Member member:memberList){
            fileSaver.println(member.getFirstName()+","+member.getLastName()+","+member.getDateOfBirth()+","+
                    member.getIsActive()+","+member.getIsCompeting()+","+member.getHasPayed()+","+
                    member.getDateOfMembership()+","+member.getMembershipNumber()+","+member.getTeam()+","+
                    member.getUsername()+","+member.getPassword()+","+member.getUserType());
        }
        fileSaver.flush();
        fileSaver.close();


        return true;
    }
}
