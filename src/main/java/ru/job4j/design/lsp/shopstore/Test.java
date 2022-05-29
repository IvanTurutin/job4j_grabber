package ru.job4j.design.lsp.shopstore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Test {
    public static void main(String[] args) throws ParseException {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();

        ControllQuality cq = new ControllQuality(warehouse, shop, trash);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar currentDate = Calendar.getInstance();
        System.out.println(simpleDateFormat.format(currentDate.getTime()));

        double coef1 = 0.8;
        Calendar createDate1 = Calendar.getInstance();
        createDate1.setTimeInMillis(currentDate.getTimeInMillis() - (int) (1000000000 * (1 - coef1)));
        Calendar expiryDate1 = Calendar.getInstance();
        expiryDate1.setTimeInMillis(currentDate.getTimeInMillis() + (int) (1000000000 * coef1));
        System.out.println(currentDate.getTimeInMillis());
        System.out.println(createDate1.getTimeInMillis());
        System.out.println(expiryDate1.getTimeInMillis());

        double coef2 = 0.5;
        Calendar createDate2 = Calendar.getInstance();
        createDate2.setTimeInMillis(currentDate.getTimeInMillis() - (int) (1000000000 * (1 - coef2)));
        Calendar expiryDate2 = Calendar.getInstance();
        expiryDate2.setTimeInMillis(currentDate.getTimeInMillis() + (int) (1000000000 * coef2));

        double coef3 = 0.15;
        Calendar createDate3 = Calendar.getInstance();
        createDate3.setTimeInMillis(currentDate.getTimeInMillis() - (int) (1000000000 * (1 - coef3)));
        Calendar expiryDate3 = Calendar.getInstance();
        expiryDate3.setTimeInMillis(currentDate.getTimeInMillis() + (int) (1000000000 * coef3));

        double coef4 = -0.1;
        Calendar createDate4 = Calendar.getInstance();
        createDate4.setTimeInMillis(currentDate.getTimeInMillis() - (int) (1000000000 * (1 - coef4)));
        Calendar expiryDate4 = Calendar.getInstance();
        expiryDate4.setTimeInMillis(currentDate.getTimeInMillis() + (int) (1000000000 * coef4));

        Food food1 = new Potatoes("food1", expiryDate1, createDate1, 100, 0.2);
        Food food2 = new Potatoes("food2", expiryDate2, createDate2, 150, 0.2);
        Food food3 = new Bread("food3", expiryDate3, createDate3, 50, 0.1);
        Food food4 = new Bread("food4", expiryDate4, createDate4, 70, 0.1);

        List<Food> foods = new ArrayList<>(List.of(food1, food2, food3, food4));

        cq.sortFoods(foods);
        System.out.println(warehouse.findAll(e -> true));
        System.out.println(shop.findAll(e -> true));
        System.out.println(trash.findAll(e -> true));
        System.out.println(food1.getDiscount());
    }
}
