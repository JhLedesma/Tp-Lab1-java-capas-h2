package tplab1;

import tplab1.application.Dpto;
import tplab1.persistency.DAO;
import tplab1.persistency.DbManager;
import tplab1.persistency.TableManager;
import tplab1.persistency.h2.DptoH2Dao;
import tplab1.persistency.h2.H2TableManager;
import tplab1.presentation.MainFrame;

public class Main {

    public static void main (String [] args) {
        DbManager dbManager = new DbManager("org.h2.Driver", "jdbc:h2:tcp://localhost/~/test", "sa", "");
        TableManager tableManager = new H2TableManager(dbManager);
        DAO<Dpto, Integer> userDAO = new DptoH2Dao(dbManager);

        tableManager.clearDb();
        Dpto dpto = new Dpto(1, "Jesus", "Ledesma");
        Dpto dpto2 = new Dpto(2, "Jesus2", "Ledesma2");
        userDAO.save(dpto);
        userDAO.save(dpto2);

        MainFrame mainFrame = new MainFrame(userDAO);
        mainFrame.show();
    }
}
