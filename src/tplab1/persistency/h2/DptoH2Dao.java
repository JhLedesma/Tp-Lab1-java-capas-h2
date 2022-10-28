package tplab1.persistency.h2;

import tplab1.application.Dpto;
import tplab1.persistency.DAO;
import tplab1.persistency.DbManager;
import tplab1.persistency.exception.NonExistentElement;

import java.util.List;

public class DptoH2Dao implements DAO<Dpto, Integer> {

    private DbManager dbManager;
    private DptoMapper dptoMapper = new DptoMapper();

    public DptoH2Dao(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    public void save(Dpto dpto) {
        try {
            if (dpto.getId() != null) {
                get(dpto.getId());
                update(dpto);
            } else {
                insert(dpto);
            }
        } catch (NonExistentElement e){
            insert(dpto);
        }
    }

    public Dpto get(Integer id) throws NonExistentElement {
        System.out.printf("Finding Dpto | Id: '%s'%n", id);
        String format = "SELECT * FROM dpto WHERE id = '%s'";
        String query = String.format(format, id);

        Dpto dpto = dbManager.executeQuery(query, dptoMapper).get(0);
        System.out.printf("Dpto Found | '%s'%n", dpto);
        return dpto;
    }

    public List<Dpto> getAll() throws NonExistentElement {
        System.out.println("Finding all of Dptos");
        String query = "SELECT * FROM dpto";

        List<Dpto> dptos = dbManager.executeQuery(query, dptoMapper);
        System.out.printf("Dptos Found  | '%s'%n", dptos);
        return dptos;
    }

    @Override
    public void delete(Integer id) {
        System.out.printf("Deleting dptos | Id: '%s'%n", id);
        String format = "DELETE FROM dpto WHERE id = '%s'";
        String sql = String.format(format, id);
        dbManager.execute(sql);
        System.out.printf("Dpto Deleted | Id: '%s'%n", id);
    }

    private void insert(Dpto dpto) {
        System.out.printf("Inserting Dpto '%s'%n", dpto);
        String format = "INSERT INTO dpto (name, surname) VALUES ('%s', '%s')";
        String sql = String.format(format, dpto.getName(), dpto.getSurname());
        dbManager.execute(sql);
        System.out.printf("Dpto Inserted '%s'%n", dpto);
    }

    private void update(Dpto dpto) {
        System.out.printf("Updating Dpto '%s'%n", dpto);
        String format = "UPDATE dpto set name = '%s', surname = '%s' WHERE id = '%s'";
        String sql = String.format(format, dpto.getName(), dpto.getSurname(), dpto.getId());
        dbManager.execute(sql);
        System.out.printf("Dpto Updated '%s'%n", dpto);
    }
}
