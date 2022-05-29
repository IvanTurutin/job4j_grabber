package ru.job4j.design.lsp.shopstore;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Trash implements Store {
    private List<Food> foodList = new ArrayList<>();


    public boolean add(Food food) {
        boolean rsl = false;
        if (Store.coeff(food) <= 0) {
            foodList.add(food);
            rsl = true;
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
