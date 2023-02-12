package tplab1.persistency.h2;

import tplab1.application.model.Dpto;
import tplab1.application.model.Input;
import tplab1.persistency.DAO;
import tplab1.persistency.DbManager;
import tplab1.persistency.exception.NonExistentElement;

import java.util.List;
import java.util.stream.Collectors;

public class DptoH2Dao implements DAO<Dpto, Integer> {

    private DbManager dbManager;
    private DptoMapper dptoMapper = new DptoMapper();
    private DAO<Input, Integer> inputDao;


    public DptoH2Dao(DbManager dbManager, DAO<Input, Integer> inputDao) {
        this.dbManager = dbManager;
        this.inputDao = inputDao;
    }

    public void save(Dpto dpto) {
        try {
            get(dpto.getId());
            update(dpto);
        } catch (NonExistentElement e) {
            insert(dpto);
        }
    }

    public Dpto get(Integer id) throws NonExistentElement {
        System.out.printf("Finding Dpto | Id: '%s'%n", id);
        String format = "SELECT * FROM dpto WHERE id = '%s'";
        String query = String.format(format, id);

        Dpto dpto = dbManager.executeQueryToObject(query, dptoMapper);
        dpto.setInputs(getInputs(id));
        System.out.printf("Dpto Found | '%s'%n", dpto);
        return dpto;
    }

    public List<Dpto> getAll() {
        System.out.println("Finding all of Dptos");
        String query = "SELECT * FROM dpto";

        List<Dpto> dptos = dbManager.executeQueryToList(query, dptoMapper);
        dptos.forEach(dpto -> dpto.setInputs(getInputs(dpto.getId())));
        System.out.printf("Dptos Found  | '%s'%n", dptos);
        return dptos;
    }

    @Override
    public void delete(Integer id) throws NonExistentElement {
        System.out.printf("Deleting dptos | Id: '%s'%n", id);
        String format = "DELETE FROM dpto WHERE id = '%s'";
        String sql = String.format(format, id);
        get(id).getInputs().forEach(input -> {
            try {
                inputDao.delete(input.getId());
            } catch (NonExistentElement e) {
                e.printStackTrace();
            }
        });
        dbManager.execute(sql);
        System.out.printf("Dpto Deleted | Id: '%s'%n", id);
    }

    private void insert(Dpto dpto) {
        System.out.printf("Inserting Dpto '%s'%n", dpto);
        String format = "INSERT INTO dpto (id, name, surname) VALUES ('%s','%s', '%s')";
        String sql = String.format(format, dpto.getId(), dpto.getName(), dpto.getSurname());
        dbManager.execute(sql);
        dpto.getInputs().forEach(input -> inputDao.save(input));
        System.out.printf("Dpto Inserted '%s'%n", dpto);
    }

    private void update(Dpto dpto) {
        System.out.printf("Updating Dpto '%s'%n", dpto);
        String format = "UPDATE dpto set name = '%s', surname = '%s' WHERE id = '%s'";
        String sql = String.format(format, dpto.getName(), dpto.getSurname(), dpto.getId());
        dbManager.execute(sql);
        dpto.getInputs().forEach(input -> inputDao.save(input));
        System.out.printf("Dpto Updated '%s'%n", dpto);
    }

    private List<Input> getInputs(Integer dptoId) {
        return inputDao.getAll().stream().filter(input -> input.getDptoId().equals(dptoId)).collect(Collectors.toList());
    }
}
