package ru.job4j.design.srp;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.function.Predicate;

@XmlRootElement(name = "employees")
public class Employees {
    List<Employee> employees;

    public Employees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employees() {
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
