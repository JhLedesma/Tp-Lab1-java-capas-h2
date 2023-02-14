package tplab1.application.model;

public class MonthlyBalance {

    private Dpto dpto;
    private Double inputAmount;
    private Double outputAmount;

    public MonthlyBalance(Dpto dpto, Double inputAmount, Double outputAmount) {
        this.dpto = dpto;
        this.inputAmount = inputAmount;
        this.outputAmount = outputAmount;
    }

    public Double getBalanceAmount() {
        return inputAmount - outputAmount;
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

    public Double getOutputAmount() {
        return outputAmount;
    }

    public void setOutputAmount(Double outputAmount) {
        this.outputAmount = outputAmount;
    }
}