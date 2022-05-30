package ru.job4j.design.lsp.shopstore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public interface Store {

    boolean add(Food food);

    List<Food> findAll(Predicate<Food> predicate);

    boolean removeAll(Predicate<Food> predicate);

    boolean remove(Food food);

    default double getExpirationPercentage(Food food) {
        Calendar now = Calendar.getInstance();
        return (food.getExpiryDate().getTimeInMillis()
                - now.getTimeInMillis())
                / (double) (food.getExpiryDate().getTimeInMillis()
                - food.getCreateDate().getTimeInMillis());
    }

    default List<Food> add(List<Food> foods) {
        List<Food> copyOfFoods = new ArrayList<>(foods);
        List<Food> rsl = new ArrayList<>();
        for (Food food : foods) {
            if (add(food)) {
                rsl.add(food);
            }
        }
        copyOfFoods.removeAll(rsl);
        return copyOfFoods;
    }

}
