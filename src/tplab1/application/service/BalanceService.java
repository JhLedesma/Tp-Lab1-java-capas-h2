package tplab1.application.service;

import tplab1.application.model.BalanceBetweenDates;
import tplab1.application.model.Input;
import tplab1.application.model.Output;
import tplab1.persistency.DAO;

public class BalanceService {

    private DAO<Input, Integer> inputDao;
    private DAO<Output, Integer> outputDao;

    public BalanceService(DAO<Input, Integer> inputDao, DAO<Output, Integer> outputDao) {
        this.inputDao = inputDao;
        this.outputDao = outputDao;
    }


    public BalanceBetweenDates getBalance(int monthFrom, int monthTo) {
        System.out.println("Building Balance Between Dates | Month From: " + monthFrom + " | Month To: " + monthTo);
        Double inputAmount = getInputSum(monthFrom, monthTo);
        Double expenseAmount = getExpenseSum(monthFrom, monthTo);
        BalanceBetweenDates balanceBetweenDates = new BalanceBetweenDates(inputAmount, expenseAmount);
        System.out.println("Building Balance Between Dates Built | " + balanceBetweenDates);
        return balanceBetweenDates;
    }


    private Double getInputSum(int monthFrom, int monthTo) {
        return inputDao.getAll().stream()
                .filter(input -> input.getDate().getMonth().getValue() >= monthFrom && input.getDate().getMonth().getValue() <= monthTo)
                .map(Input::getAmount)
                .reduce(Double::sum)
                .orElse(0.0);
    }

    private Double getExpenseSum(int monthFrom, int monthTo) {
        return outputDao.getAll().stream()
                .filter(expense -> expense.getDate().getMonth().getValue() >= monthFrom && expense.getDate().getMonth().getValue() <= monthTo)
                .map(Output::getAmount)
                .reduce(Double::sum)
                .orElse(0.0);
    }
}
