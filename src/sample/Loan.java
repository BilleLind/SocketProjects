package sample;

public class Loan implements java.io.Serializable {
    private double interest;
    private int years;
    private double amount;


    public Loan(double interest, int years, double amount) {
        this.interest = interest;
        this.years = years;
        this.amount = amount;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public double getMonthlyPayment() {
        double monthlyInterestRate = interest / 1200;
        double monthlyPayment =  amount * monthlyInterestRate / (1-(1/Math.pow(1 + monthlyInterestRate, years *12)));
        return monthlyPayment;
    }

    public double getTotalPayment() {
        double totalPayment = getMonthlyPayment() * years *12;
        return totalPayment;
    }

}
