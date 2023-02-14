package tplab1.application.model;

import java.util.Objects;

public class MonthlyBalance {

    private Dpto dpto;
    private String inputAmount;
    private String outputAmount;
    private String balanceAmount;

    public MonthlyBalance(Dpto dpto, String inputAmount, String outputAmount, String balanceAmount) {
        this.dpto = dpto;
        this.inputAmount = inputAmount;
        this.outputAmount = outputAmount;
        this.balanceAmount = balanceAmount;
    }

    public Dpto getDpto() {
        return dpto;
    }

    public void setDpto(Dpto dpto) {
        this.dpto = dpto;
    }

    public String getInputAmount() {
        return inputAmount;
    }

    public void setInputAmount(String inputAmount) {
        this.inputAmount = inputAmount;
    }

    public String getOutputAmount() {
        return outputAmount;
    }

    public void setOutputAmount(String outputAmount) {
        this.outputAmount = outputAmount;
    }

    public String getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(String balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthlyBalance that = (MonthlyBalance) o;
        return Objects.equals(dpto, that.dpto) && Objects.equals(inputAmount, that.inputAmount) && Objects.equals(outputAmount, that.outputAmount) && Objects.equals(balanceAmount, that.balanceAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dpto, inputAmount, outputAmount, balanceAmount);
    }

    @Override
    public String toString() {
        return "MonthlyBalance{" +
                "dpto=" + dpto +
                ", inputAmount='" + inputAmount + '\'' +
                ", outputAmount='" + outputAmount + '\'' +
                ", balanceAmount='" + balanceAmount + '\'' +
                '}';
    }
}