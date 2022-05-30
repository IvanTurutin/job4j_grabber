package ru.job4j.design.lsp;

import org.junit.Test;
import ru.job4j.design.lsp.shopstore.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ControllQualityTest {

    @Test
    public void sortFoods() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();

        ControllQuality cq = new ControllQuality(warehouse, shop, trash);

        Calendar currentDate = Calendar.getInstance();

        var sixtyDayInMillis = 1000L * 60L * 60L * 24L * 60L;

        double coef1 = 1;
        Calendar createDate1 = Calendar.getInstance();
        createDate1.setTimeInMillis(currentDate.getTimeInMillis() - (long) (sixtyDayInMillis * (1 - coef1)));
        Calendar expiryDate1 = Calendar.getInstance();
        expiryDate1.setTimeInMillis(currentDate.getTimeInMillis() + (long) (sixtyDayInMillis * coef1));

        double coef2 = 0.5;
        Calendar createDate2 = Calendar.getInstance();
        createDate2.setTimeInMillis(currentDate.getTimeInMillis() - (long) (sixtyDayInMillis * (1 - coef2)));
        Calendar expiryDate2 = Calendar.getInstance();
        expiryDate2.setTimeInMillis(currentDate.getTimeInMillis() + (long) (sixtyDayInMillis * coef2));

        double coef3 = 0.15;
        Calendar createDate3 = Calendar.getInstance();
        createDate3.setTimeInMillis(currentDate.getTimeInMillis() - (long) (sixtyDayInMillis * (1 - coef3)));
        Calendar expiryDate3 = Calendar.getInstance();
        expiryDate3.setTimeInMillis(currentDate.getTimeInMillis() + (long) (sixtyDayInMillis * coef3));

        double coef4 = -0.1;
        Calendar createDate4 = Calendar.getInstance();
        createDate4.setTimeInMillis(currentDate.getTimeInMillis() - (long) (sixtyDayInMillis * (1 - coef4)));
        Calendar expiryDate4 = Calendar.getInstance();
        expiryDate4.setTimeInMillis(currentDate.getTimeInMillis() + (long) (sixtyDayInMillis * coef4));

        Food food1 = new Potatoes("food1", expiryDate1, createDate1, 100, 0.2);
        Food food2 = new Potatoes("food2", expiryDate2, createDate2, 150, 0.2);
        Food food3 = new Bread("food3", expiryDate3, createDate3, 50, 0.1);
        Food food4 = new Bread("food4", expiryDate4, createDate4, 70, 0.1);

        List<Food> foods = new ArrayList<>(List.of(food1, food2, food3, food4));

        List<Food> rsl = cq.sortFoods(foods);

        assertThat(warehouse.findAll(f -> true).get(0),
                is(new Potatoes("food1", expiryDate1, createDate1, 100, 0.2)));
        assertTrue(shop.findAll(f -> true)
                .contains(new Potatoes("food2", expiryDate2, createDate2, 150, 0.2)));
        assertTrue(shop.findAll(f -> true)
                .contains(new Bread("food3", expiryDate3, createDate3, 45, 0.1)));
        assertThat(shop.findAll(f -> "food3".equals(f.getName())).get(0).getPrice(),
                closeTo(45, 0.00001));
        assertThat(trash.findAll(f -> true).get(0),
                is(new Bread("food4", expiryDate4, createDate4, 70, 0.1)));
        assertThat(rsl.size(), is(0));
    }
}