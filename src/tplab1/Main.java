package tplab1;

import tplab1.application.User;
import tplab1.persistency.DAO;
import tplab1.persistency.DbManager;
import tplab1.persistency.TableManager;
import tplab1.persistency.h2.H2TableManager;
import tplab1.persistency.h2.UserH2DAO;
import tplab1.presentation.MainFrame;

public class Main {

    public static void main (String [] args) {
        DbManager dbManager = new DbManager("org.h2.Driver", "jdbc:h2:tcp://localhost/~/test", "sa", "");
        TableManager tableManager = new H2TableManager(dbManager);
        DAO<User, String> userDAO = new UserH2DAO(dbManager);

        tableManager.clearDb();
        User user = new User("Jesus", "Ledesma");
        User user2 = new User("Jesus2", "Ledesma2");
        userDAO.save(user);
        userDAO.save(user2);

        MainFrame mainFrame = new MainFrame(userDAO);
        mainFrame.show();
    }
}
