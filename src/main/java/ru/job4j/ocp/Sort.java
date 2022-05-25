package ru.job4j.ocp;

import ru.job4j.design.srp.Employee;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *	Класс осуществляет сортировку списка.
 *	Ошибка заключается в том, что параметры и возвращаемые типы методов и полей привязаны к конкретным
 *	реалзациям сущностей (ArrayList и Employee).
 *  Применение реализации списка (ArrayList) не позволяет передавать в методы и возвращать из них
 *  другие типы списков. Для решения этой проблемы необходимо использовать интерфейс List.
 *	Применение конкретной реализации Employee приведет к необходимости изменять методы при сортировке
 *	других типов. Для решения этой проблемы необходимо ввести переменные типа, либо расширить возможность
 *	применения других типов, которые реализуют необходимый интерфейс.
 */

public class Sort {

    ArrayList<Employee> target;

    public ArrayList<Employee> sortDsc(ArrayList<Employee> value, Comparator<Employee> comparator) {
        return sort(value, comparator.reversed());
    }
    public ArrayList<Employee> sortAsc(ArrayList<Employee> value, Comparator<Employee> comparator) {
        return sort(value, comparator);
    }

    private ArrayList<Employee> sort(ArrayList<Employee> value, Comparator<Employee> comparator) {
        target = new ArrayList<>(value);
        target.sort(comparator);
        return target;
    }
}
