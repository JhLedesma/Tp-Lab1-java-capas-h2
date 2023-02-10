package tplab1.persistency.h2;

import tplab1.application.Input;
import tplab1.persistency.DAO;
import tplab1.persistency.DbManager;
import tplab1.persistency.exception.NonExistentElement;

import java.util.List;

public class InputH2Dao implements DAO<Input, Integer> {

    private DbManager dbManager;
    private InputMapper InputMapper = new InputMapper();

    public InputH2Dao(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    public void save(Input input) {
        try {
            get(input.getId());
            update(input);
        } catch (NonExistentElement e) {
            insert(input);
        }
    }

    public Input get(Integer id) throws NonExistentElement {
        System.out.printf("Finding Input | Id: '%s'%n", id);
        String format = "SELECT * FROM input WHERE id = '%s'";
        String query = String.format(format, id);

        Input Input = dbManager.executeQuery(query, InputMapper).get(0);
        System.out.printf("Input Found | '%s'%n", Input);
        return Input;
    }

    public List<Input> getAll() throws NonExistentElement {
        System.out.println("Finding all of Inputs");
        String query = "SELECT * FROM input";

        List<Input> Inputs = dbManager.executeQuery(query, InputMapper);
        System.out.printf("Inputs Found  | '%s'%n", Inputs);
        return Inputs;
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
        String format = "INSERT INTO input (id, amount, description, dptoId) VALUES ('%s','%s','%s','%s')";
        String sql = String.format(format, input.getId(), input.getAmount(), input.getDescription(), input.getDptoId());
        dbManager.execute(sql);
        System.out.printf("Input Inserted '%s'%n", input);
    }

    private void update(Input input) {
        System.out.printf("Updating Input '%s'%n", input);
        String format = "UPDATE input set amount = '%s', description = '%s', dptoId = '%s' WHERE id = '%s'";
        String sql = String.format(format, input.getAmount(), input.getDescription(), input.getDptoId(), input.getId());
        dbManager.execute(sql);
        System.out.printf("Input Updated '%s'%n", input);
    }
}
