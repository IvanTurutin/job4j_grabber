package ru.job4j.design.srp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportEngineHR implements Report {

    private Store store;
    private Comparator<Employee> comparator;

    public ReportEngineHR(Store store, Comparator<Employee> comparator) {
        this.store = store;
        this.comparator = comparator;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
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
