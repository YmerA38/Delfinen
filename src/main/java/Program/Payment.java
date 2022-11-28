package Program;

import Main.Database;
import Program.Member;


public class Payment {
    static Database database = new Database();

    public static double checkIncomeEstimate() {
        double incomeEstimate = 0;
        for (int i = 0; i < database.getMemberList().size(); i++) {
            incomeEstimate += database.getMemberList().get(i).getSubscribtionRate();
        }

        return incomeEstimate;
    }
}
