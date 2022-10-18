package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Ви", "Смитт", (byte) 27);
        System.out.println("User с именем Ви добавлен в базу данных");

        userDao.saveUser("Джеки", "Уэллс", (byte) 30);
        System.out.println("User с именем Джеки добавлен в базу данных");

        userDao.saveUser("Джонни", "Сильверхенд", (byte) 88);
        System.out.println("User с именем Джонни добавлен в базу данных");

        userDao.saveUser("Панам", "Палмер", (byte) 26);
        System.out.println("User с именем Панам добавлен в базу данных");

        userDao.removeUserById(1);
        /*userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();*/

    }
}