package ru.job4j.ocp;

import ru.job4j.design.srp.Employee;

import java.util.Comparator;
import java.util.List;

/**
 *	Класс осуществляет поиск максимума/минимума.
 *	Ошибка заключается в том, что параметры и возвращаемые типы метода привязаны к конкретной реалзации.
 *	При необходимости добавления поиска максимума/минимума для других типов потребуется изменять данный класс.
 *	Необходимо ввести параметризацию типов, либо расширить возможность применения других реализаций
 *	при помощи интерфейса.
 */

public class MaxMin {

    public Employee max(List<Employee> value, Comparator<Employee> comparator) {
        return minMax(value, comparator.reversed());
    }
    public Employee min(List<Employee> value, Comparator<Employee> comparator) {
        return minMax(value, comparator);
    }

    private Employee minMax(List<Employee> value, Comparator<Employee> comparator) {
        Employee target = value.get(0);
        for (Employee tmp : value) {
            if (comparator.compare(target, tmp) >= 0) {
                target = tmp;
            }
        }
        return target;
    }
}
