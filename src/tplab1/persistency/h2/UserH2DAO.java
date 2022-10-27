package tplab1.persistency.h2;

import tplab1.application.User;
import tplab1.persistency.DAO;
import tplab1.persistency.DbManager;
import tplab1.persistency.exception.NonExistentElement;

import java.util.List;

public class UserH2DAO implements DAO<User, String> {

    private DbManager dbManager;
    private UserMapper userMapper = new UserMapper();

    public UserH2DAO(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    public void save(User user) {
        try {
            get(user.getName());
            update(user);
        } catch (NonExistentElement e){
            insert(user);
        }
    }

    public User get(String name) throws NonExistentElement {
        System.out.printf("Finding user | Name: '%s'%n", name);
        String format = "SELECT * FROM users WHERE name = '%s'";
        String query = String.format(format, name);

        User user = dbManager.executeQuery(query, userMapper).get(0);
        System.out.printf("User Found  | '%s'%n", user);
        return user;
    }

    public List<User> getAll() throws NonExistentElement {
        System.out.printf("Finding all of users");
        String query = "SELECT * FROM users";

        List<User> users = dbManager.executeQuery(query, userMapper);
        System.out.printf("Users Found  | '%s'%n", users);
        return users;
    }

    @Override
    public void delete(String id) {
        System.out.printf("Deleting users | Id: '%s'%n", id);
        String format = "DELETE FROM users WHERE name = '%s'";
        String sql = String.format(format, id);
        dbManager.execute(sql);
        System.out.printf("User Deleted | Id: '%s'%n", id);
    }

    private void insert(User user) {
        System.out.printf("Inserting '%s'%n", user);
        String format = "INSERT INTO users (name, surname) VALUES ('%s', '%s')";
        String sql = String.format(format, user.getName(), user.getSurname());
        dbManager.execute(sql);
        System.out.printf("User Inserted '%s'%n", user);
    }

    private void update(User user) {
        System.out.printf("Updating '%s'%n", user);
        String format = "UPDATE users set name = '%s', surname = '%s' WHERE name = '%s'";
        String sql = String.format(format, user.getName(), user.getSurname(), user.getName());
        dbManager.execute(sql);
        System.out.printf("User Updated '%s'%n", user);
    }
}
