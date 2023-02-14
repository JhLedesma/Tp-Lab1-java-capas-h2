package tplab1.application.service;

import tplab1.application.exception.ApplicationException;
import tplab1.application.model.Dpto;
import tplab1.application.model.Input;
import tplab1.application.model.MonthlyBalance;
import tplab1.application.model.Output;
import tplab1.persistency.DAO;
import tplab1.persistency.exception.NonExistentElement;

import java.util.List;
import java.util.stream.Collectors;

public class MonthlyBalanceService {

    private DAO<Input, Integer> inputDao;
    private DAO<Output, Integer> outputDao;
    private DAO<Dpto, Integer> dptoDAO;

    public MonthlyBalanceService(DAO<Input, Integer> inputDao, DAO<Output, Integer> outputDao, DAO<Dpto, Integer> dptoDAO) {
        this.inputDao = inputDao;
        this.outputDao = outputDao;
        this.dptoDAO = dptoDAO;
    }

    public MonthlyBalance getBalance(int month, Integer dptoId) {
        System.out.println("Building Monthly Balance | Month: " + month + " | Dpto Id: " + dptoId);
        try {
            int dptosCount = dptoDAO.getAll().size();
            Dpto dpto = dptoDAO.get(dptoId);
            Double inputAmount = getInputSum(month, dptoId);
            Double expenseAmount = getExpenseSum(month) / dptosCount;
            MonthlyBalance monthlyBalance = new MonthlyBalance(dpto, inputAmount, expenseAmount);
            System.out.println("Monthly Balance Built | " + monthlyBalance);
            return monthlyBalance;
        } catch (NonExistentElement e) {
            throw new ApplicationException("Dpto is not exist | Id: " + dptoId);
        }
    }

    public String[] getDptosIds() {
        List<String> ids = dptoDAO.getAll().stream().map(dpto -> dpto.getId().toString()).collect(Collectors.toList());
        String[] items = new String[ids.size()];
        return ids.toArray(items);
    }

    public String[] getMonths() {
        return new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    }

    private Double getInputSum(int month, Integer dptoId) {
        return inputDao.getAll().stream()
                .filter(input -> input.getDate().getMonth().getValue() == month && input.getDptoId().equals(dptoId))
                .map(Input::getAmount)
                .reduce(Double::sum)
                .orElse(0.0);
    }

    private Double getExpenseSum(int month) {
        return outputDao.getAll().stream()
                .filter(expense -> expense.getDate().getMonth().getValue() == month)
                .map(Output::getAmount)
                .reduce(Double::sum)
                .orElse(0.0);
    }
}
