package tplab1;

import tplab1.application.model.Dpto;
import tplab1.application.model.Habitant;
import tplab1.application.model.Input;
import tplab1.application.service.DptoService;
import tplab1.persistency.DAO;
import tplab1.persistency.DbBootstrapping;
import tplab1.persistency.DbManager;
import tplab1.persistency.TableManager;
import tplab1.persistency.h2.DptoH2Dao;
import tplab1.persistency.h2.H2TableManager;
import tplab1.persistency.h2.HabitantH2Dao;
import tplab1.persistency.h2.InputH2Dao;
import tplab1.presentation.MainFrame;
import tplab1.presentation.dpto.DptoController;

public class Main {

    public static void main (String [] args) {
        DbManager dbManager = new DbManager("org.h2.Driver", "jdbc:h2:tcp://localhost/~/test", "sa", "");
        TableManager tableManager = new H2TableManager(dbManager);
        DAO<Input, Integer> inputDAO = new InputH2Dao(dbManager);
        DAO<Habitant, Integer> habitantDAO = new HabitantH2Dao(dbManager);
        DAO<Dpto, Integer> dptoDAO = new DptoH2Dao(dbManager, inputDAO, habitantDAO);
        DptoService dptoService = new DptoService(dptoDAO);

        new DbBootstrapping(tableManager, dptoDAO).exec();

        DptoController dptoController = new DptoController(dptoService);

        MainFrame mainFrame = new MainFrame(dptoController);
        mainFrame.show();
    }
}
