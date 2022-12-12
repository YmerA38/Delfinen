package Program;

import Main.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private final String RESULT_SAVE = "data/resultDatabase.csv";
    private final String FILE_MEMBER = "data/memberDatabase.csv";
    private final String FILE_PAYMENT = "data/payments/paymentMember_";
    private final String FILE_PAYMENT_EXT = ".csv";
    private final String FILE_LETTER = "autoText/paymentLetter.txt";

    public boolean load(Database database) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File(FILE_MEMBER));
        String line;
        String[] entity;
        String[] date1;
        String[] date2;
        fileScanner.nextLine();//spring linje over
        do {
            line = fileScanner.nextLine();
            if (!line.isEmpty()) {
                entity = line.split(",");
                date1 = entity[2].split("-");
                date2 = entity[6].split("-");
                database.addMember(entity[0], entity[1], LocalDate.of(Integer.parseInt(date1[0]),
                        Integer.parseInt(date1[1]), Integer.parseInt(date1[2])),
                        Boolean.parseBoolean(entity[3]), Boolean.parseBoolean(entity[4]),
                        Boolean.parseBoolean(entity[5]), LocalDate.of(Integer.parseInt(date2[0]),
                        Integer.parseInt(date2[1]), Integer.parseInt(date2[2])), Integer.parseInt(entity[7]),
                        Team.valueOf(entity[8]), entity[9], entity[10], Users.valueOf(entity[11]), LocalDate.parse(entity[12]));
            }
        } while (fileScanner.hasNextLine() && !line.isEmpty());

        return true;
    }

    public boolean save(ArrayList<Member> memberList) throws FileNotFoundException {
        PrintStream fileSaver = new PrintStream(FILE_MEMBER);
        fileSaver.println("Navn,Efternavn,Fødselsdato,aktiv,konkurrence,betalt,oprettet,nr,Hold,Brugernavn," +
                "kode,Adgangstype,Betal.Dato");
        for (Member member : memberList) {
            fileSaver.println(member.getFirstName() + "," + member.getLastName() + "," + member.getDateOfBirth() + "," +
                    member.getIsActive() + "," + member.getIsCompeting() + "," + member.getHasPayed() + "," +
                    member.getDateOfMembership() + "," + member.getMembershipNumber() + "," + member.getTeam() + "," +
                    member.getUsername() + "," + member.getPassword() + "," + member.getUserType() + "," + member.getNextPayment());
        }
        fileSaver.flush();
        fileSaver.close();
        return true;
    }


    public boolean savePayment(Member member) {

        File file = new File(FILE_PAYMENT + member.getMembershipNumber() + FILE_PAYMENT_EXT);

        try {
            PrintStream fileSaver = new PrintStream(file);
            fileSaver.println("Dato,Kontingent,Betaling");
            for (AccountTransaction transaction : member.getPaymentList()) {
                fileSaver.println(transaction.getDate() + "," + transaction.getSubscription() + "," + transaction.getPayment());
            }
            fileSaver.flush();
            fileSaver.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }

    }

    public boolean loadPayment(Member member) {
        try {
            Scanner fileScanner = new Scanner(new File(FILE_PAYMENT + member.getMembershipNumber() + FILE_PAYMENT_EXT));
            String line;
            String[] part;
            if(fileScanner.hasNextLine()) {
                fileScanner.nextLine();
            }
            do {
                line = fileScanner.nextLine();
                if (!line.isEmpty()) {
                    part = line.split(",");
                    member.getPaymentList().add(new AccountTransaction(LocalDate.parse(part[0]), Double.parseDouble(part[1]),
                            Double.parseDouble(part[2])));
                }
            } while (fileScanner.hasNextLine() && !line.isEmpty());
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public boolean saveResult(Database database){
        File saveFile = new File (RESULT_SAVE);
        int no;
        try {
            PrintStream fileSaver = new PrintStream(saveFile);
            fileSaver.println("MedlemsNr,Diciplin,Tid,Distance[100m],Konkurence");
            for(Member member : database.getMemberList()) {
                if(member instanceof CompeteSwimmer) {
                    no = member.getMembershipNumber();
                    for (Results results : ((CompeteSwimmer) member).getResultList()) {
                        fileSaver.println(no + "," + results.getDiscipline() + "," + results.getTime() + "," + results.getDistance() + "," +
                                results.getCompetitionName());
                    }
                    fileSaver.println("-".repeat(50));
                }
            }
            fileSaver.flush();
            fileSaver.close();
            return true;
        }catch (FileNotFoundException e){
            return false;
        }

    }

    public void loadResults(Database database) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File(RESULT_SAVE));
        fileScanner.nextLine();
        String line ="";
        String[] part;

        do{
            if(fileScanner.hasNextLine()) {
                line = fileScanner.nextLine();
                if(line.charAt(0)=='-'){
                    fileScanner.nextLine();
                }
                if(!line.isEmpty()){
                    part = line.split(",");
                    for (Member member: database.getMemberList()){
                        if(member.getMembershipNumber()==Integer.parseInt(part[0])){
                            ((CompeteSwimmer)member).getResultList().add(new Results(Discipline.valueOf(part[1]),
                                    Double.parseDouble(part[2]),Integer.parseInt(part[3]),part[4]));
                        }
                    }

                }
            }
        }while(!line.isEmpty()&&fileScanner.hasNextLine());

        for (Member member: database.getMemberList()){



        }
    }
}