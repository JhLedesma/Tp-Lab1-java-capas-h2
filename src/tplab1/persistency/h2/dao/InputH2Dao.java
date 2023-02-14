package tplab1.persistency.h2.dao;

import tplab1.application.model.Input;
import tplab1.persistency.DAO;
import tplab1.persistency.DbManager;
import tplab1.persistency.exception.NonExistentElement;
import tplab1.persistency.h2.mapper.InputMapper;

import java.util.List;

public class InputH2Dao implements DAO<Input, Integer> {

    private DbManager dbManager;
    private tplab1.persistency.h2.mapper.InputMapper InputMapper = new InputMapper();

    public InputH2Dao(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    public void save(Input input) {
        try {
            if (input.getId() != null) {
                get(input.getId());
                update(input);
            } else {
                insert(input);
            }
        } catch (NonExistentElement e) {
            insert(input);
        }
    }

    public Input get(Integer id) throws NonExistentElement {
        System.out.printf("Finding Input | Id: '%s'%n", id);
        String format = "SELECT * FROM input WHERE id = '%s'";
        String query = String.format(format, id);

        Input input = dbManager.executeQueryToObject(query, InputMapper);
        System.out.printf("Input Found | '%s'%n", input);
        return input;
    }

    public List<Input> getAll() {
        System.out.println("Finding all of Inputs");
        String query = "SELECT * FROM input";

        List<Input> inputs = dbManager.executeQueryToList(query, InputMapper);
        System.out.printf("Inputs Found  | '%s'%n", inputs);
        return inputs;
    }

    @Override
    public void delete(Integer id) {
        System.out.printf("Deleting Inputs | Id: '%s'%n", id);
        String format = "DELETE FROM input WHERE id = '%s'";
        String sql = String.format(format, id);
        dbManager.execute(sql);
        System.out.printf("Input Deleted | Id: '%s'%n", id);
    }

    private void insert(Input input) {
        System.out.printf("Inserting Input '%s'%n", input);
        String sql;
        if (input.getId() != null) {
            String format = "INSERT INTO input (id, amount, description, date, dptoId) VALUES ('%s','%s','%s','%s','%s')";
            sql = String.format(format, input.getId(), input.getAmount(), input.getDescription(), input.getDate(), input.getDptoId());
        } else {
            String format = "INSERT INTO input (amount, description, date, dptoId) VALUES ('%s','%s','%s','%s')";
            sql = String.format(format, input.getAmount(), input.getDescription(), input.getDate(), input.getDptoId());
        }
        dbManager.execute(sql);
        System.out.printf("Input Inserted '%s'%n", input);
    }

    private void update(Input input) {
        System.out.printf("Updating Input '%s'%n", input);
        String format = "UPDATE input set amount = '%s', description = '%s', date = '%s', dptoId = '%s' WHERE id = '%s'";
        String sql = String.format(format, input.getAmount(), input.getDescription(), input.getDate(), input.getDptoId(), input.getId());
        dbManager.execute(sql);
        System.out.printf("Input Updated '%s'%n", input);
    }
}
