package tplab1.persistency.h2;

import tplab1.application.User;
import tplab1.persistency.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        String userName = resultSet.getString("name");
        String userSurname = resultSet.getString("surname");
        return new User(userName, userSurname);
    }
}
