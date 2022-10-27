package tplab1.persistency.dbmanager;

import tplab1.persistency.exception.PersistencyException;

import java.sql.Connection;
import java.sql.SQLException;

public class RollbackSql {

    public void execute(Connection connection, SQLException e) {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new PersistencyException("The db driver couldn't rollback | error message: " + e.getMessage());
        }
    }
}
