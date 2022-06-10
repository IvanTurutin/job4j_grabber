package ru.job4j.design.lsp.shopstore;

import java.util.Calendar;

public class Potatoes extends Food {

    public Potatoes(String name, Calendar expiryDate, Calendar createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }

    public Potatoes(String name, Calendar expiryDate, Calendar createDate, double price) {
        super(name, expiryDate, createDate, price);
    }

}
