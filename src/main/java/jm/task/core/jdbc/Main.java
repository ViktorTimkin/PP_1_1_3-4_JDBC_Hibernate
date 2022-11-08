package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    private static final UserService userService = new UserServiceImpl();
    public static void main(String[] args) throws SQLException {

        userService.createUsersTable();

        userService.saveUser("Ви", "Смитт", (byte) 27);
        userService.saveUser("Джеки", "Уэллс", (byte) 30);
        userService.saveUser("Джонни", "Сильверхенд", (byte) 88);
        userService.saveUser("Панам", "Палмер", (byte) 26);
        userService.saveUser("Виктор", "Вектор", (byte) 40);

        List <User> users = userService.getAllUsers();
        for (User user:users) {
            System.out.println(user);
        }

        userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}