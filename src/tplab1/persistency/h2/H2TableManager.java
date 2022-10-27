package tplab1.persistency.h2;

import tplab1.persistency.DbManager;
import tplab1.persistency.TableManager;

public class H2TableManager implements TableManager {

    private DbManager dbManager;

    public H2TableManager(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    public void createUserTable() {
        String query = "CREATE TABLE IF NOT EXISTS users (id IDENTITY NOT NULL PRIMARY KEY, name VARCHAR(256), surname VARCHAR(256))";
        dbManager.execute(query);
    }

    public void dropUserTable() {
        String query = "DROP TABLE users";
        dbManager.execute(query);
    }
}
