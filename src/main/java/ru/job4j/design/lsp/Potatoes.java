package ru.job4j.design.lsp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Potatoes extends Food {

    public Potatoes(String name, Calendar expiryDate, Calendar createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }

}
