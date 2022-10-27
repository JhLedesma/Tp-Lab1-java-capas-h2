package tplab1.persistency;

import tplab1.persistency.exception.NonExistentElement;
import tplab1.persistency.exception.PersistencyException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbManager {

    private final String DB_DRIVER;
    private final String DB_BASE_URL;
    private final String DB_USERNAME;
    private final String DB_PASSWORD;

    public DbManager(String dbDriver, String dbBaseUrl, String dbUsername, String dbPassword) {
        DB_DRIVER = dbDriver;
        DB_BASE_URL = dbBaseUrl;
        DB_USERNAME = dbUsername;
        DB_PASSWORD = dbPassword;
    }

    public void execute(String sql) {
        Connection connection = connect();
        try {
            connect();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            connection.commit();
        } catch (SQLException e) {
            rollback(connection, e);
            throw new PersistencyException("The query: " + sql + " wasn't successful | errorMessage: " + e.getMessage());
        } finally {
            disconnect(connection);
        }
    }

    public <T> List<T> executeQuery(String query, Connection connection, Mapper<T> mapper) throws NonExistentElement {
        List<T> list = new ArrayList<>();
        ResultSet resultSet = getResultSet(query, connection);
        try {
            while (resultSet.next()) {
                T object = mapper.map(resultSet);
                list.add(object);
            }
        } catch (SQLException e) {
            throw new PersistencyException(createErrorResultMessage(query, e));
        } finally {
            disconnect(connection);
        }
        if (!list.isEmpty()) {
            return list;
        } else {
            throw new NonExistentElement("The result doesn't exist to the query: " + query);
        }
    }

    public Connection connect() {
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

    public void disconnect(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new PersistencyException("The db driver couldn't disconnect | error message: " + e.getMessage());
        }
    }

    public void rollback(Connection connection, SQLException e) {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new PersistencyException("The db driver couldn't rollback | error message: " + e.getMessage());
        }
    }

    private ResultSet getResultSet(String query, Connection connection) {
        ResultSet result;
        try {
            connect();
            Statement statement = connection.createStatement();
            result = statement.executeQuery(query);
            connection.commit();
        } catch (SQLException e) {
            rollback(connection, e);
            disconnect(connection);
            throw new PersistencyException("The query: " + query + " wasn't successful | errorMessage: " + e.getMessage());
        }
        return result;
    }

    private String createErrorResultMessage(String query, SQLException e) {
        return "Error while the result was got: " + query + " | error message: " + e.getMessage();
    }
}
