package ru.job4j.design.srp;

import com.google.gson.GsonBuilder;

import java.util.function.Predicate;

public class ReportEngineJSON implements Report {

    private Store store;

    public ReportEngineJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        var gson = new GsonBuilder().create();
        return gson.toJson(new Employees(store.findBy(filter)));
    }
}
