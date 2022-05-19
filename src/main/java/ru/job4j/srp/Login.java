package ru.job4j.srp;

import java.sql.Connection;
/**
 * Класс который проверяет наличие логина и пароля пользователя в базе данных.
 * Ошибка заключается в том, что класс помимо проверки логина и пароля сам
 * осуществляет установление соединения с базой данных.Что нарушает принцип единственной ответственности.
 * То есть метода getConnection() тут быть не должно. Соединение должно передаваться либо напрямую в метод
 * через аргументы, либо через конструктор.
 */

public class Login {
    private Connection connection;

    public boolean getConnection() {
        boolean rsl = false;
        try {
            /*получение соединения с БД*/
            rsl = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public boolean loginValidation(String login, String pass) {
        boolean rsl = false;
        try {
            if (connection.isValid(0)) {
                /**
                 * проверка логина и пароля
                 */
                rsl = true;
            } else {
                getConnection();
                /**
                 * проверка логина и пароля
                 */
                rsl = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

}
