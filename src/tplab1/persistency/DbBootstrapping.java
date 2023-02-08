package tplab1.persistency;

import tplab1.application.Dpto;

public class DbBootstrapping {

    private TableManager tableManager;
    private DAO<Dpto, Integer> dptoDAO;

    public DbBootstrapping(TableManager tableManager, DAO<Dpto, Integer> dptoDAO) {
        this.tableManager = tableManager;
        this.dptoDAO = dptoDAO;
    }

    public void exec() {
        tableManager.clearDb();
        Dpto dpto = new Dpto(1, "Jesus", "Ledesma");
        Dpto dpto2 = new Dpto(2, "Lucas", "Alario");
        Dpto dpto5 = new Dpto(5, "Pablo", "Barrios");
        Dpto dpto6 = new Dpto(6, "Florencia", "Fernandez");
        Dpto dpto10 = new Dpto(10, "Cristian", "Palacios");
        Dpto dpto12 = new Dpto(12, "Lucia", "Reyes");
        Dpto dpto14 = new Dpto(12, "Carlos", "Rosas");
        Dpto dpto15 = new Dpto(15, "Norma", "Sarmiento");
        Dpto dpto17 = new Dpto(17, "Martin", "Lopez");
        Dpto dpto19 = new Dpto(19, "Laura", "Alvarez");
        Dpto dpto20 = new Dpto(20, "Marcela", "Gonzalez");
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
