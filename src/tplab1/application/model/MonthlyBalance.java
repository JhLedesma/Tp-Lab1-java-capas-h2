package tplab1.application.model;

public class MonthlyBalance {

    private Dpto dpto;
    private Double inputAmount;
    private Double expenseAmount;

    public MonthlyBalance(Dpto dpto, Double inputAmount, Double expenseAmount) {
        this.dpto = dpto;
        this.inputAmount = inputAmount;
        this.expenseAmount = expenseAmount;
    }

    public Double getBalanceAmount() {
        return inputAmount - expenseAmount;
    }

    public Dpto getDpto() {
        return dpto;
    }

    public void setDpto(Dpto dpto) {
        this.dpto = dpto;
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