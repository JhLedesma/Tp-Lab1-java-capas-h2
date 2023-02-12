package tplab1.persistency.h2;

import tplab1.application.model.Habitant;
import tplab1.persistency.DAO;
import tplab1.persistency.DbManager;
import tplab1.persistency.exception.NonExistentElement;

import java.util.List;

public class HabitantH2Dao implements DAO<Habitant, Integer> {

    private DbManager dbManager;
    private HabitantMapper habitantMapper = new HabitantMapper();

    public HabitantH2Dao(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    public void save(Habitant habitant) {
        try {
            get(habitant.getDni());
            update(habitant);
        } catch (NonExistentElement e) {
            insert(habitant);
        }
    }

    public Habitant get(Integer id) throws NonExistentElement {
        System.out.printf("Finding Habitant | Dni: '%s'%n", id);
        String format = "SELECT * FROM habitant WHERE dni = '%s'";
        String query = String.format(format, id);

        Habitant habitant = dbManager.executeQueryToObject(query, habitantMapper);
        System.out.printf("Habitant Found | '%s'%n", habitant);
        return habitant;
    }

    public List<Habitant> getAll() {
        System.out.println("Finding all of Habitants");
        String query = "SELECT * FROM habitant";

        List<Habitant> habitants = dbManager.executeQueryToList(query, habitantMapper);
        System.out.printf("Habitants Found  | '%s'%n", habitants);
        return habitants;
    }

    @Override
    public void delete(Integer id) {
        System.out.printf("Deleting Habitants | Dni: '%s'%n", id);
        String format = "DELETE FROM habitant WHERE dni = '%s'";
        String sql = String.format(format, id);
        dbManager.execute(sql);
        System.out.printf("Habitant Deleted | Dni: '%s'%n", id);
    }

    private void insert(Habitant habitant) {
        System.out.printf("Inserting Habitant '%s'%n", habitant);
        String format = "INSERT INTO habitant (dni, name, surname, dptoId) VALUES ('%s','%s','%s','%s')";
        String sql = String.format(format, habitant.getDni(), habitant.getName(), habitant.getSurname(), habitant.getDptoId());
        dbManager.execute(sql);
        System.out.printf("habitant Inserted '%s'%n", habitant);
    }

    private void update(Habitant habitant) {
        System.out.printf("Updating Habitant '%s'%n", habitant);
        String format = "UPDATE habitant set name = '%s', surname = '%s', dptoId = '%s' WHERE dni = '%s'";
        String sql = String.format(format, habitant.getName(), habitant.getSurname(), habitant.getDptoId(), habitant.getDni());
        dbManager.execute(sql);
        System.out.printf("Habitant Updated '%s'%n", habitant);
    }
}