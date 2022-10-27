package tplab1.persistency.dbmanager;

import tplab1.persistency.exception.PersistencyException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteSql {

    private ConnectDB connect;
    private RollbackSql rollback;
    private DisconnectDB disconnect;

    public ExecuteSql(ConnectDB connect, RollbackSql rollback, DisconnectDB disconnect) {
        this.connect = connect;
        this.rollback = rollback;
        this.disconnect = disconnect;
    }

    public void execute(String sql) {
        Connection connection = connect.execute();
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
            connection.commit();
        } catch (SQLException e) {
            rollback.execute(connection, e);
            throw new PersistencyException("The query: " + sql + " wasn't successful | errorMessage: " + e.getMessage());
        } finally {
            disconnect.execute(connection);
        }
    }
}
