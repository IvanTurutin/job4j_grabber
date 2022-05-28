package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Shop implements Store {
    private List<Food> foodList = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        double coeff = Store.coeff(food);
        if (coeff <= 0.75 && coeff > 0.25) {
            foodList.add(food);
        } else if (coeff <= 0.25 && coeff > 0) {
            food.setPrice(food.getPrice() * (1 - food.getDiscount()));
            foodList.add(food);
        }
        return rsl;
    }

    @Override
    public List<Food> add(List<Food> foods) {
        List<Food> rsl = new ArrayList<>(foods);
        for (int i = 0; i < foods.size(); i++) {
            if (add(foods.get(i))) {
                rsl.remove(i);
            }
        }
        return rsl;
    }

    @Override
    public List<Food> findAll(Predicate<Food> predicate) {
        return foodList.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public boolean removeAll(Predicate<Food> predicate) {
        return foodList.removeAll(foodList.stream().filter(predicate).collect(Collectors.toList()));
    }

    @Override
    public boolean remove(Food food) {
        return foodList.remove(food);
    }
}
