package tplab1.persistency;

import tplab1.persistency.dbmanager.*;
import tplab1.persistency.exception.NonExistentElement;

import java.util.List;

public class DbManager {

    private ConnectDB connectDB;
    private DisconnectDB disconnectDB = new DisconnectDB();
    private RollbackSql rollbackSql = new RollbackSql();
    private ExecuteSql executeSql;
    private ExecuteQuery executeQuery;

    public DbManager(String dbDriver, String dbBaseUrl, String dbUsername, String dbPassword) {
        this.connectDB = new ConnectDB(dbDriver, dbBaseUrl, dbUsername, dbPassword);
        this.executeSql = new ExecuteSql(connectDB, rollbackSql, disconnectDB);
        this.executeQuery = new ExecuteQuery(connectDB, rollbackSql, disconnectDB);
    }

    public <T> List<T> executeQuery(String query, Mapper<T> mapper) throws NonExistentElement {
        return executeQuery.execute(query, mapper);
    }

    public void execute(String sql) {
        executeSql.execute(sql);
    }
}
