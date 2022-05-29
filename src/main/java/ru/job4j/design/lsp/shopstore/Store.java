package ru.job4j.design.lsp.shopstore;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public interface Store {

    boolean add(Food food);

    List<Food> add(List<Food> foods);

    List<Food> findAll(Predicate<Food> predicate);

    boolean removeAll(Predicate<Food> predicate);

    boolean remove(Food food);

    static double coeff(Food food) {
        Calendar now = Calendar.getInstance();
        return (food.getExpiryDate().getTimeInMillis()
                - now.getTimeInMillis())
                / (double) (food.getExpiryDate().getTimeInMillis()
                - food.getCreateDate().getTimeInMillis());
    }
}
