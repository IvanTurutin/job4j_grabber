package ru.job4j.design.lsp.shopstore;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Shop implements Store {
    private List<Food> foodList = new ArrayList<>();
    private static final float ALLOWABLE_PERCENTAGE_TOP = 0.75f;
    private static final float ALLOWABLE_PERCENTAGE_DISCOUNT = 0.25f;
    private static final double DISCOUNT_EXCLUSION = 0;

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        double coeff = getExpirationPercentage(food);
        if (coeff <= ALLOWABLE_PERCENTAGE_TOP && coeff > ALLOWABLE_PERCENTAGE_DISCOUNT) {
            foodList.add(food);
            rsl = true;
        } else if (coeff <= ALLOWABLE_PERCENTAGE_DISCOUNT && coeff > 0) {
            food.setPrice(food.getPrice() * (1 - food.getDiscount()));
            food.setDiscount(DISCOUNT_EXCLUSION);
            foodList.add(food);
            rsl = true;
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
