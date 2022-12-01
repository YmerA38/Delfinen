package Program;

import Main.Database;


public class Subscription {
    static Database database = new Database();

    private int subscribtionRate;
    private double payment;


    /*public static double checkIncomeEstimate() {
        double incomeEstimate = 0;
        for (int i = 0; i < database.getMemberList().size(); i++) {
            incomeEstimate += database.getMemberList().get(i).getSubscribtionRate();
        }

        return incomeEstimate;
    }*/
}

