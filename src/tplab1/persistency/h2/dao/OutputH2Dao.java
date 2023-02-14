package tplab1.persistency.h2.dao;

import tplab1.application.model.Output;
import tplab1.persistency.DAO;
import tplab1.persistency.DbManager;
import tplab1.persistency.Mapper;
import tplab1.persistency.exception.NonExistentElement;
import tplab1.persistency.h2.mapper.OutputMapper;

import java.util.List;

public class OutputH2Dao implements DAO<Output, Integer> {

    private DbManager dbManager;
    private Mapper<Output> outputMapper = new OutputMapper();

    public OutputH2Dao(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    public void save(Output output) {
        try {
            if(output.getId() != null) {
                get(output.getId());
                update(output);
            } else {
                insert(output);
            }
        } catch (NonExistentElement e) {
            insert(output);
        }
    }

    public Output get(Integer id) throws NonExistentElement {
        System.out.printf("Finding Output | Id: '%s'%n", id);
        String format = "SELECT * FROM output WHERE id = '%s'";
        String query = String.format(format, id);

        Output output = dbManager.executeQueryToObject(query, outputMapper);
        System.out.printf("Output Found | '%s'%n", output);
        return output;
    }

    public List<Output> getAll() {
        System.out.println("Finding all of Outputs");
        String query = "SELECT * FROM output";

        List<Output> expens = dbManager.executeQueryToList(query, outputMapper);
        System.out.printf("outputs Found  | '%s'%n", expens);
        return expens;
    }

    @Override
    public void delete(Integer id) {
        System.out.printf("Deleting Output | Id: '%s'%n", id);
        String format = "DELETE FROM output WHERE id = '%s'";
        String sql = String.format(format, id);
        dbManager.execute(sql);
        System.out.printf("Output Deleted | Id: '%s'%n", id);
    }

    private void insert(Output output) {
        System.out.printf("Inserting Output '%s'%n", output);
        String sql;
        if(output.getId() != null) {
            String format = "INSERT INTO output (id, amount, description, date) VALUES ('%s','%s','%s','%s')";
            sql = String.format(format, output.getId(), output.getAmount(), output.getDescription(), output.getDate());
        } else {
            String format = "INSERT INTO output (amount, description, date) VALUES ('%s','%s','%s')";
            sql = String.format(format, output.getAmount(), output.getDescription(), output.getDate());
        }
        dbManager.execute(sql);
        System.out.printf("output Inserted '%s'%n", output);
    }

    private void update(Output output) {
        System.out.printf("Updating output '%s'%n", output);
        String format = "UPDATE output set amount = '%s', description = '%s', date = '%s' WHERE id = '%s'";
        String sql = String.format(format, output.getAmount(), output.getDescription(), output.getDate(), output.getId());
        dbManager.execute(sql);
        System.out.printf("Output Updated '%s'%n", output);
    }
}
