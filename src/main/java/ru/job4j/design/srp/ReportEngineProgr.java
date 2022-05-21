package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportEngineProgr implements Report {

    private Store store;

    public ReportEngineProgr(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        var ls = System.lineSeparator();
        text.append("<html>").append(ls)
                .append("<body>").append(ls)
                .append("<p>")
                .append("Name; Hired; Fired; Salary;")
                .append("</p>")
                .append(ls);
        for (Employee employee : store.findBy(filter)) {
            text.append("<p>")
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append("</p>")
                    .append(ls);
        }
        text.append("</body>").append(ls).append("</html>").append(ls);
        return text.toString();
    }
}