package tplab1.persistency.h2.dao;

import tplab1.application.model.Expense;
import tplab1.persistency.DAO;
import tplab1.persistency.DbManager;
import tplab1.persistency.Mapper;
import tplab1.persistency.exception.NonExistentElement;
import tplab1.persistency.h2.mapper.ExpenseMapper;

import java.util.List;

public class ExpenseH2Dao implements DAO<Expense, Integer> {

    private DbManager dbManager;
    private Mapper<Expense> expenseMapper = new ExpenseMapper();

    public ExpenseH2Dao(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    public void save(Expense expense) {
        try {
            get(expense.getId());
            update(expense);
        } catch (NonExistentElement e) {
            insert(expense);
        }
    }

    public Expense get(Integer id) throws NonExistentElement {
        System.out.printf("Finding Expense | Id: '%s'%n", id);
        String format = "SELECT * FROM expense WHERE id = '%s'";
        String query = String.format(format, id);

        Expense expense = dbManager.executeQueryToObject(query, expenseMapper);
        System.out.printf("Expense Found | '%s'%n", expense);
        return expense;
    }

    public List<Expense> getAll() {
        System.out.println("Finding all of Expenses");
        String query = "SELECT * FROM expense";

        List<Expense> expenses = dbManager.executeQueryToList(query, expenseMapper);
        System.out.printf("expenses Found  | '%s'%n", expenses);
        return expenses;
    }

    @Override
    public void delete(Integer id) {
        System.out.printf("Deleting Expense | Id: '%s'%n", id);
        String format = "DELETE FROM expense WHERE id = '%s'";
        String sql = String.format(format, id);
        dbManager.execute(sql);
        System.out.printf("Expense Deleted | Id: '%s'%n", id);
    }

    private void insert(Expense expense) {
        System.out.printf("Inserting Expense '%s'%n", expense);
        String format = "INSERT INTO expense (id, amount, description, date) VALUES ('%s','%s','%s','%s')";
        String sql = String.format(format, expense.getId(), expense.getAmount(), expense.getDescription(), expense.getDate());
        dbManager.execute(sql);
        System.out.printf("expense Inserted '%s'%n", expense);
    }

    private void update(Expense expense) {
        System.out.printf("Updating expense '%s'%n", expense);
        String format = "UPDATE expense set amount = '%s', description = '%s', date = '%s' WHERE id = '%s'";
        String sql = String.format(format, expense.getAmount(), expense.getDescription(), expense.getDate(), expense.getId());
        dbManager.execute(sql);
        System.out.printf("Expense Updated '%s'%n", expense);
    }
}
