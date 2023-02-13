package tplab1.persistency;

import tplab1.application.model.Dpto;
import tplab1.application.model.Habitant;

public class DbBootstrapping {

    private TableManager tableManager;
    private DAO<Dpto, Integer> dptoDAO;

    public DbBootstrapping(TableManager tableManager, DAO<Dpto, Integer> dptoDAO) {
        this.tableManager = tableManager;
        this.dptoDAO = dptoDAO;
    }

    public void exec() {
        tableManager.clearDb();

        Dpto dpto = new Dpto(1, "A", 35);
        Dpto dpto2 = new Dpto(2, "A", 35);
        Dpto dpto5 = new Dpto(5, "A", 35);
        Dpto dpto6 = new Dpto(6, "B", 35);
        Dpto dpto10 = new Dpto(10, "B", 40);
        Dpto dpto12 = new Dpto(12, "C", 40);
        Dpto dpto14 = new Dpto(14, "C", 40);
        Dpto dpto15 = new Dpto(15, "C", 40);
        Dpto dpto17 = new Dpto(17, "D", 45);
        Dpto dpto19 = new Dpto(19, "D", 45);
        Dpto dpto20 = new Dpto(20, "D", 45);

        Habitant habitant = new Habitant(113012, "Jesus", "Ledesma", dpto.getId());
        Habitant habitant2 = new Habitant(221023, "Lucas", "Alario", dpto2.getId());
        Habitant habitant5 = new Habitant(533011, "Pablo", "Barrios", dpto5.getId());
        Habitant habitant6 = new Habitant(632200, "Florencia", "Fernandez", dpto6.getId());
        Habitant habitant10 = new Habitant(103011, "Cristian", "Palacios", dpto10.getId());
        Habitant habitant12 = new Habitant(123012, "Lucia", "Reyes", dpto12.getId());
        Habitant habitant14 = new Habitant(143012, "Carlos", "Rosas", dpto14.getId());
        Habitant habitant15 = new Habitant(153022, "Norma", "Sarmiento", dpto15.getId());
        Habitant habitant17 = new Habitant(177012, "Martin", "Lopez", dpto17.getId());
        Habitant habitant19 = new Habitant(193999, "Laura", "Alvarez", dpto19.getId());
        Habitant habitant20 = new Habitant(203733, "Marcela", "Gonzalez", dpto20.getId());

        dpto.setHabitant(habitant);
        dpto2.setHabitant(habitant2);
        dpto5.setHabitant(habitant5);
        dpto6.setHabitant(habitant6);
        dpto10.setHabitant(habitant10);
        dpto12.setHabitant(habitant12);
        dpto14.setHabitant(habitant14);
        dpto15.setHabitant(habitant15);
        dpto17.setHabitant(habitant17);
        dpto19.setHabitant(habitant19);
        dpto20.setHabitant(habitant20);

        dptoDAO.save(dpto);
        dptoDAO.save(dpto2);
        dptoDAO.save(dpto5);
        dptoDAO.save(dpto6);
        dptoDAO.save(dpto10);
        dptoDAO.save(dpto12);
        dptoDAO.save(dpto14);
        dptoDAO.save(dpto15);
        dptoDAO.save(dpto17);
        dptoDAO.save(dpto19);
        dptoDAO.save(dpto20);
    }
}
