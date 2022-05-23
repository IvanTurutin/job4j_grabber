package ru.job4j.ocp;

import ru.job4j.design.srp.Employee;

import java.util.Comparator;
import java.util.List;

/**
 * Класс Store описывает методы по работе с базой данных сотруников компании. Изначально он содержал
 * методы save() и findAll(). Метод save() сохранял список сотрудников в БД. Метод findAll() считывал
 * из БД всех сотрудников. Затем потребовалось, чтобы список сотруников записывался в БД в отсортированном
 * виде. Для этого в метод save() была добавлена сортировка по компаратору, а сам компаратор передавался
 * в конструктор класса.
 * Ошибка заключается в том, что был изменен исходный класс, который может использоваться еще где-то,
 * при этом в другом месте сортировка при записи не требуется. В данном случае нужно было расширить
 * класс Store и в новом классе переопределить метод save() добавив в него дополнительный функционал.
 */

public class Store {

    Comparator<Employee> comparator;

    /* Конструктор добавлен по просьбе одного из отделов*/
    public Store(Comparator<Employee> comparator) {
        this.comparator = comparator;
    }


    public boolean save(List<Employee> employeeList) {
        employeeList.sort(comparator); /* Эта строка добавлен по просьбе одного из отделов*/
        /*код, записывающий список работников в БД*/
        return false;
    }

    public List<Employee> findAll() {
        /*код считывающий всех сотрудников из БД*/
        return null;
    }
}
