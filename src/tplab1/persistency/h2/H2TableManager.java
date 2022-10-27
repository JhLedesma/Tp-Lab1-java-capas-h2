package tplab1.persistency.h2;

import tplab1.persistency.DbManager;
import tplab1.persistency.TableManager;

public class H2TableManager implements TableManager {

    private DbManager dbManager;

    public H2TableManager(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    public void createUserTable() {
        System.out.println("Creating User Table");
        String query = "CREATE TABLE IF NOT EXISTS users (id IDENTITY NOT NULL PRIMARY KEY, name VARCHAR(256), surname VARCHAR(256))";
        dbManager.execute(query);
        System.out.println("User Table Created");
    }

    public void dropUserTable() {
        System.out.println("Dropping User Table");
        String query = "DROP TABLE IF EXISTS users";
        dbManager.execute(query);
        System.out.println("User Table Dropped");
    }

    public void clearDb() {
        System.out.println("Clearing DB");
        dropUserTable();
        createUserTable();
        System.out.println("DB Cleared");
    }
}
