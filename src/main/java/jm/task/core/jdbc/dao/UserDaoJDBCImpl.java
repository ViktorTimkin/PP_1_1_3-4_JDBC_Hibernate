package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;


public class UserDaoJDBCImpl implements UserDao {
    private static Connection connection;

    public UserDaoJDBCImpl() throws SQLException {
        connection = getConnection();
    }

    public void createUsersTable() throws SQLException {

        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.execute("CREATE TABLE IF NOT EXISTS CyberpunkUsers (id int not null AUTO_INCREMENT,name VARCHAR(45),lastname VARCHAR(45),age int, PRIMARY KEY (id))");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            }
            catch (SQLException er) {
                throw new RuntimeException (er);
            }
        }
    }

    public void dropUsersTable() throws SQLException {

        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.execute("DROP TABLE IF EXISTS CyberpunkUsers");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            }
            catch (SQLException er) {
                throw new RuntimeException (er);
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CyberpunkUsers (name, lastname, age) VALUES (?, ?, ?)")) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            }
            catch (SQLException er) {
                throw new RuntimeException (er);
            }
        }
    }

    public void removeUserById(long id) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM CyberpunkUsers WHERE id = ?")) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            }
            catch (SQLException er) {
                throw new RuntimeException (er);
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CyberpunkUsers")) {
            connection.setAutoCommit(false);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void cleanUsersTable() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.execute("DELETE FROM CyberpunkUsers");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            }catch (SQLException er){
                throw new RuntimeException();
            }
        }
    }
}
