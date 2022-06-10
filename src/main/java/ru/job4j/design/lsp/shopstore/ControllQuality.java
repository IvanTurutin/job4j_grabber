package ru.job4j.design.lsp.shopstore;

import java.util.ArrayList;
import java.util.List;

public class ControllQuality {

    private Store[] stores;

    public ControllQuality(Store... stores) {
        this.stores = stores;
    }

    public List<Food> sortFoods(List<Food> foods) {
        List<Food> foodList = new ArrayList<>(foods);
        for (Store store : stores) {
            foodList = store.add(foodList);
        }
        return foodList;
    }

    public List<Food> resort() {
        List<Food> foodList = new ArrayList<>();
        for (Store store : stores) {
            foodList.addAll(store.findAll(e -> true));
            store.removeAll(e -> true);
        }
        return sortFoods(foodList);
    }

}
