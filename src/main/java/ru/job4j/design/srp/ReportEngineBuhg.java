package ru.job4j.design.srp;

import java.text.DecimalFormat;
import java.util.function.Predicate;

public class ReportEngineBuhg implements Report {

    private Store store;
    private DecimalFormat df;

    public ReportEngineBuhg(Store store, DecimalFormat df) {
        this.store = store;
        this.df = df;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        var ls = System.lineSeparator();
        text.append("Name; Hired; Fired; Salary;").append(ls);
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(df.format(employee.getSalary())).append(";")
                    .append(ls);
        }
        return text.toString();
    }
}