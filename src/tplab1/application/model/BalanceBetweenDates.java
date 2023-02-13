package tplab1.application.model;

public class BalanceBetweenDates {

    private Double inputAmount;
    private Double expenseAmount;

    public BalanceBetweenDates(Double inputAmount, Double expenseAmount) {
        this.inputAmount = inputAmount;
        this.expenseAmount = expenseAmount;
    }

    public Double getBalanceAmount() {
        return inputAmount - expenseAmount;
    }

    public Double getInputAmount() {
        return inputAmount;
    }

    public void setInputAmount(Double inputAmount) {
        this.inputAmount = inputAmount;
    }

    public Double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(Double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }
}