package tplab1.application.service;

import tplab1.application.exception.ApplicationException;
import tplab1.application.model.*;
import tplab1.persistency.DAO;
import tplab1.persistency.exception.NonExistentElement;

public class BalanceService {

    private DAO<Input, Integer> inputDao;
    private DAO<Expense, Integer> expenseDao;
    private DAO<Dpto, Integer> dptoDAO;

    public BalanceService(DAO<Input, Integer> inputDao, DAO<Expense, Integer> expenseDao, DAO<Dpto, Integer> dptoDAO) {
        this.inputDao = inputDao;
        this.expenseDao = expenseDao;
        this.dptoDAO = dptoDAO;
    }

    public MonthlyBalance getBalance(int month, Integer dptoId) {
        System.out.println("Building Monthly Balance | Month: " + month + " | Dpto Id: " + dptoId);
        try {
            Dpto dpto = dptoDAO.get(dptoId);
            Double inputAmount = getInputSum(month, dptoId);
            Double expenseAmount = getExpenseSum(month);
            MonthlyBalance monthlyBalance = new MonthlyBalance(dpto, inputAmount, expenseAmount);
            System.out.println("Monthly Balance Built | " + monthlyBalance);
            return monthlyBalance;
        } catch (NonExistentElement e) {
            throw new ApplicationException("Dpto is not exist | Id: " + dptoId);
        }
    }

    public BalanceBetweenDates getBalance(int monthFrom, int monthTo) {
        System.out.println("Building Balance Between Dates | Month From: " + monthFrom + " | Month To: " + monthTo);
        Double inputAmount = getInputSum(monthFrom, monthTo);
        Double expenseAmount = getExpenseSum(monthFrom, monthTo);
        BalanceBetweenDates balanceBetweenDates = new BalanceBetweenDates(inputAmount, expenseAmount);
        System.out.println("Building Balance Between Dates Built | " + balanceBetweenDates);
        return balanceBetweenDates;
    }

    private Double getInputSum(int month, Integer dptoId) {
        return inputDao.getAll().stream()
                .filter(input -> input.getDate().getMonth().getValue() == month && input.getDptoId().equals(dptoId))
                .map(Input::getAmount)
                .reduce(Double::sum)
                .orElse(0.0);
    }

    private Double getExpenseSum(int month) {
        return expenseDao.getAll().stream()
                .filter(expense -> expense.getDate().getMonth().getValue() == month)
                .map(Expense::getAmount)
                .reduce(Double::sum)
                .orElse(0.0);
    }

    private Double getInputSum(int monthFrom, int monthTo) {
        return inputDao.getAll().stream()
                .filter(input -> input.getDate().getMonth().getValue() >= monthFrom && input.getDate().getMonth().getValue() <= monthTo)
                .map(Input::getAmount)
                .reduce(Double::sum)
                .orElse(0.0);
    }

    private Double getExpenseSum(int monthFrom, int monthTo) {
        return expenseDao.getAll().stream()
                .filter(expense -> expense.getDate().getMonth().getValue() >= monthFrom && expense.getDate().getMonth().getValue() <= monthTo)
                .map(Expense::getAmount)
                .reduce(Double::sum)
                .orElse(0.0);
    }
}
