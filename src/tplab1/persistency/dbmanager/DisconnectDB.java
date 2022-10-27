package tplab1.persistency.dbmanager;

import tplab1.persistency.exception.PersistencyException;

import java.sql.Connection;
import java.sql.SQLException;

public class DisconnectDB {

    public void execute(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new PersistencyException("The db driver couldn't disconnect | error message: " + e.getMessage());
        }
    }
}
