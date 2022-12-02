package Program;

import java.time.LocalDate;

public class AccountTransaction {
    private double subscription;
    private double payment;
    private LocalDate date;

    public AccountTransaction() {
    }

    public AccountTransaction( LocalDate date,double subscription, double payment) {
        this.subscription = subscription;
        this.payment = payment;
        this.date = date;
    }

    public void setPayment(double payment){
        this.payment = payment;
        this.subscription = 0;
        this.date = LocalDate.now();
    }
    public void setSubscription(double subscription,LocalDate dateOfSubscription){
        this.payment = 0;
        this.subscription = subscription;
        this.date = dateOfSubscription;
    }

    public double getSubscription() {
        return subscription;
    }

    public double getPayment() {
        return payment;
    }

    public LocalDate getDate() {
        return date;
    }
}
