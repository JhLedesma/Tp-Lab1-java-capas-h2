package tplab1.application.service;

import tplab1.application.exception.ApplicationException;
import tplab1.application.model.Dpto;
import tplab1.application.model.Expense;
import tplab1.application.model.Input;
import tplab1.application.model.MonthlyBalance;
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
            Double expenseAmount = getExpenseSum(month, dptoId);
            MonthlyBalance monthlyBalance = new MonthlyBalance(dpto, inputAmount, expenseAmount);
            System.out.println("Monthly Balance Build | " + monthlyBalance);
            return monthlyBalance;
        } catch (NonExistentElement e) {
            throw new ApplicationException("Dpto is not exist | Id: " + dptoId);
        }
    }

    public void getBalance(int monthFrom, int monthTo) {

    }

    private Double getInputSum(int month, Integer dptoId) {
        return inputDao.getAll().stream()
                .filter(input -> input.getDate().getMonth().getValue() == month && input.getDptoId().equals(dptoId))
                .map(Input::getAmount)
                .reduce(Double::sum)
                .orElse(0.0);
    }

    private Double getExpenseSum(int month, Integer dptoId) {
        return expenseDao.getAll().stream()
                .filter(input -> input.getDate().getMonth().getValue() == month && input.getDptoId().equals(dptoId))
                .map(Expense::getAmount)
                .reduce(Double::sum)
                .orElse(0.0);
    }
}
