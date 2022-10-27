package tplab1.persistency.dbmanager;

import tplab1.persistency.Mapper;
import tplab1.persistency.exception.NonExistentElement;
import tplab1.persistency.exception.PersistencyException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExecuteQuery {

    private ConnectDB connect;
    private RollbackSql rollback;
    private DisconnectDB disconnect;

    public ExecuteQuery(ConnectDB connect, RollbackSql rollback, DisconnectDB disconnect) {
        this.connect = connect;
        this.rollback = rollback;
        this.disconnect = disconnect;
    }

    public <T> List<T> execute(String query, Mapper<T> mapper) throws NonExistentElement {
        Connection connection = connect.execute();
        List<T> list = new ArrayList<>();
        ResultSet resultSet = getResultSet(query, connection);
        try {
            while (resultSet.next()) {
                T object = mapper.map(resultSet);
                list.add(object);
            }
        } catch (SQLException e) {
            throw new PersistencyException("Error while the result was got: " + query + " | error message: " + e.getMessage());
        } finally {
            disconnect.execute(connection);
        }
        if (!list.isEmpty()) {
            return list;
        } else {
            throw new NonExistentElement("The result doesn't exist to the query: " + query);
        }
    }

    private ResultSet getResultSet(String query, Connection connection) {
        ResultSet result;
        try {
            connect.execute();
            Statement statement = connection.createStatement();
            result = statement.executeQuery(query);
            connection.commit();
        } catch (SQLException e) {
            rollback.execute(connection, e);
            disconnect.execute(connection);
            throw new PersistencyException("The query: " + query + " wasn't successful | errorMessage: " + e.getMessage());
        }
        return result;
    }
}
