package tplab1.persistency.h2;

import tplab1.persistency.DbManager;
import tplab1.persistency.TableManager;

public class H2TableManager implements TableManager {

    private DbManager dbManager;

    public H2TableManager(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    public void createDtoTable() {
        System.out.println("Creating Dpto Table");
        String query = "CREATE TABLE IF NOT EXISTS dpto (id IDENTITY NOT NULL PRIMARY KEY, name VARCHAR(256), surname VARCHAR(256))";
        dbManager.execute(query);
        System.out.println("Dpto Table Created");
    }

    public void dropDptoTable() {
        System.out.println("Dropping Dpto Table");
        String query = "DROP TABLE IF EXISTS dpto";
        dbManager.execute(query);
        System.out.println("Dpto Table Dropped");
    }

    public void clearDb() {
        System.out.println("Clearing DB");
        dropDptoTable();
        createDtoTable();
        System.out.println("DB Cleared");
    }
}
