package tplab1.persistency.h2;

import tplab1.persistency.DbManager;
import tplab1.persistency.TableManager;

public class H2TableManager implements TableManager {

    private DbManager dbManager;

    public H2TableManager(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public void createTables() {
        createDtoTable();
        createInputTable();
    }

    @Override
    public void dropTables() {
        dropDptoTable("input");
        dropDptoTable("dpto");
    }

    @Override
    public void clearDb() {
        System.out.println("Clearing DB");
        dropTables();
        createTables();
        System.out.println("DB Cleared");
    }

    private void createDtoTable() {
        System.out.println("Creating Dpto Table");
        String query = "CREATE TABLE IF NOT EXISTS dpto ( id IDENTITY NOT NULL PRIMARY KEY, name VARCHAR(256), surname VARCHAR(256) );";
        dbManager.execute(query);
        System.out.println("Dpto Table Created");
    }

    private void createInputTable() {
        System.out.println("Creating Dpto Table");
        String query = "CREATE TABLE IF NOT EXISTS input ( id IDENTITY NOT NULL PRIMARY KEY," +
                " amount DECIMAL, description VARCHAR(256), date TIMESTAMP, dptoId INT, " +
                "FOREIGN KEY (dptoId) REFERENCES dpto(id) );";
        dbManager.execute(query);
        System.out.println("Input Table Created");
    }

    private void dropDptoTable(String table) {
        System.out.println("Dropping " + table + "Table");
        String query = "DROP TABLE IF EXISTS " + table;
        dbManager.execute(query);
        System.out.println(table + " Table Dropped");
    }
}
