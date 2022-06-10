package ru.job4j.design.lsp;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.lsp.shopstore.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ControllQualityTest {

    private Store warehouse;
    private Store shop;
    private Store trash;
    private Calendar currentDate = Calendar.getInstance();
    private long sixtyDayInMillis = 1000L * 60L * 60L * 24L * 60L;
    private ControllQuality cq;
    private List<Food> foods;
    private Calendar createDate1 = Calendar.getInstance();
    private Calendar expiryDate1 = Calendar.getInstance();
    private Calendar createDate2 = Calendar.getInstance();
    private Calendar expiryDate2 = Calendar.getInstance();
    private Calendar createDate3 = Calendar.getInstance();
    private Calendar expiryDate3 = Calendar.getInstance();
    private Calendar createDate4 = Calendar.getInstance();
    private Calendar expiryDate4 = Calendar.getInstance();
    private Food food1;
    private Food food2;
    private Food food3;
    private Food food4;


    @Before
    public void initObjects() {

        warehouse = new Warehouse();
        shop = new Shop();
        trash = new Trash();

        cq = new ControllQuality(warehouse, shop, trash);

        double coef1 = 1;
        createDate1.setTimeInMillis(currentDate.getTimeInMillis() - (long) (sixtyDayInMillis * (1 - coef1)));
        expiryDate1.setTimeInMillis(currentDate.getTimeInMillis() + (long) (sixtyDayInMillis * coef1));

        double coef2 = 0.5;
        createDate2.setTimeInMillis(currentDate.getTimeInMillis() - (long) (sixtyDayInMillis * (1 - coef2)));
        expiryDate2.setTimeInMillis(currentDate.getTimeInMillis() + (long) (sixtyDayInMillis * coef2));

        double coef3 = 0.15;
        createDate3.setTimeInMillis(currentDate.getTimeInMillis() - (long) (sixtyDayInMillis * (1 - coef3)));
        expiryDate3.setTimeInMillis(currentDate.getTimeInMillis() + (long) (sixtyDayInMillis * coef3));

        double coef4 = -0.1;
        createDate4.setTimeInMillis(currentDate.getTimeInMillis() - (long) (sixtyDayInMillis * (1 - coef4)));
        expiryDate4.setTimeInMillis(currentDate.getTimeInMillis() + (long) (sixtyDayInMillis * coef4));

        food1 = new Potatoes("food1", expiryDate1, createDate1, 100, 0.2);
        food2 = new Potatoes("food2", expiryDate2, createDate2, 150, 0.2);
        food3 = new Bread("food3", expiryDate3, createDate3, 50, 0.1);
        food4 = new Bread("food4", expiryDate4, createDate4, 70, 0.1);

        foods = new ArrayList<>(List.of(food1, food2, food3, food4));

    }

    @Test
    public void whenSortFoods() {

        List<Food> rsl = cq.sortFoods(foods);

        assertThat(warehouse.findAll(f -> true).get(0),
                is(new Potatoes("food1", expiryDate1, createDate1, 100)));
        assertTrue(shop.findAll(f -> true)
                .contains(new Potatoes("food2", expiryDate2, createDate2, 150)));
        assertTrue(shop.findAll(f -> true)
                .contains(new Bread("food3", expiryDate3, createDate3, 45)));
        assertThat(shop.findAll(f -> "food3".equals(f.getName())).get(0).getPrice(),
                closeTo(45, 0.00001));
        assertThat(trash.findAll(f -> true).get(0),
                is(new Bread("food4", expiryDate4, createDate4, 70)));
        assertThat(rsl.size(), is(0));
    }

    @Test
    public void whenReSortFoods() {
        cq.sortFoods(foods);
        food1.setCreateDate(createDate2);
        food1.setExpiryDate(expiryDate2);
        food2.setCreateDate(createDate3);
        food2.setExpiryDate(expiryDate3);
        food3.setCreateDate(createDate4);
        food3.setExpiryDate(expiryDate4);
        food4.setCreateDate(createDate1);
        food4.setExpiryDate(expiryDate1);

        List<Food> rsl = cq.resort();
        assertThat(warehouse.findAll(f -> true).get(0),
                is(new Bread("food4", expiryDate1, createDate1, 70)));
        assertTrue(shop.findAll(f -> true)
                .contains(new Potatoes("food1", expiryDate2, createDate2, 100)));
        assertTrue(shop.findAll(f -> true)
                .contains(new Potatoes("food2", expiryDate3, createDate3, 120)));
        assertTrue(trash.findAll(f -> true)
                .contains(new Bread("food3", expiryDate4, createDate4, 45)));
        assertThat(rsl.size(), is(0));
    }

    @Test
    public void whenSortFoodsTwicePriceDiscountedOnce() {
        cq.sortFoods(foods);
        cq.resort();

        assertThat(shop.findAll(f -> "food3".equals(f.getName())).get(0).getPrice(),
                closeTo(45, 0.00001));
    }

}