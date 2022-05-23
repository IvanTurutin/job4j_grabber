package ru.job4j.ocp;

import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.Store;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 *	Класс формирует отчет с использованием компаратора.
 *	Ошибка заключается в том, что компаратор формируется внутри метода класса,
 *	вместо того, чтобы передавать его в качестве параметра. Это приводит к тому, что
 *	приходится каждый раз править код метода или создавать дополнительный класс/метод
 *	для изменения логики сортировки.
 */

public class Report {

    private Store store;

    public Report(Store store) {
        this.store = store;
    }

    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        Comparator<Employee> comparator = (o1, o2) -> Double.compare(o2.getSalary(), o1.getSalary());
        var ls = System.lineSeparator();
        text.append("Name; Salary;").append(ls);
        List<Employee> employees = store.findBy(filter);
        employees.sort(comparator);
        for (Employee employee : employees) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(ls);
        }
        return text.toString();
    }

}
