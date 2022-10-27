package tplab1.persistency.dbmanager;

import tplab1.persistency.exception.PersistencyException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    private final String DB_DRIVER;
    private final String DB_BASE_URL;
    private final String DB_USERNAME;
    private final String DB_PASSWORD;

    public ConnectDB(String dbDriver, String dbBaseUrl, String dbUsername, String dbPassword) {
        this.DB_DRIVER = dbDriver;
        this.DB_BASE_URL = dbBaseUrl;
        this.DB_USERNAME = dbUsername;
        this.DB_PASSWORD = dbPassword;
    }

    public Connection execute() {
        Connection connection;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new PersistencyException("The db driver couldn't run | error message: " + e.getMessage());
        }
        try {
            connection = DriverManager.getConnection(DB_BASE_URL, DB_USERNAME, DB_PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new PersistencyException("The db driver couldn't connect | error message: " + e.getMessage());
        }
        return connection;
    }
}
