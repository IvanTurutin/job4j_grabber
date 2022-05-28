package ru.job4j.design.lsp;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

public class StoreTest {

    @Test
    public void whenCoeffIsZeroPointOneFive() {

        Calendar currentDate = Calendar.getInstance();

        double coef = 0.15;
        long sixtyDayInMillis = 1000L * 60 * 60 * 24 * 60;
        Calendar createDate = Calendar.getInstance();
        createDate.setTimeInMillis(currentDate.getTimeInMillis() - (int) (sixtyDayInMillis * (1 - coef)));
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTimeInMillis(currentDate.getTimeInMillis() + (int) (sixtyDayInMillis * coef));

        Food food = new Potatoes("food1", expiryDate, createDate, 100, 0.2);

        assertThat(Store.coeff(food), closeTo(coef, 0.00001));
    }

    @Test
    public void whenWarehouseAddFood() {
        Calendar currentDate = Calendar.getInstance();

        double coef = 0.751;
        long sixtyDayInMillis = 1000L * 60 * 60 * 24 * 60;
        Calendar createDate = Calendar.getInstance();
        createDate.setTimeInMillis(currentDate.getTimeInMillis() - (int) (sixtyDayInMillis * (1 - coef)));
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTimeInMillis(currentDate.getTimeInMillis() + (int) (sixtyDayInMillis * coef));

        Food food = new Potatoes("food1", expiryDate, createDate, 100, 0.2);

        Store store = new Warehouse();
        store.add(food);
        assertThat(store.findAll(f -> true).get(0),
                is(new Potatoes("food1", expiryDate, createDate, 100, 0.2)));
    }

    @Test
    public void whenWarehouseNotAddFood() {
        Calendar currentDate = Calendar.getInstance();

        double coef = 0.749;
        long sixtyDayInMillis = 1000L * 60 * 60 * 24 * 60;
        Calendar createDate = Calendar.getInstance();
        createDate.setTimeInMillis(currentDate.getTimeInMillis() - (int) (sixtyDayInMillis * (1 - coef)));
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTimeInMillis(currentDate.getTimeInMillis() + (int) (sixtyDayInMillis * coef));

        Food food = new Potatoes("food1", expiryDate, createDate, 100, 0.2);

        Store store = new Warehouse();
        store.add(food);
        assertThat(store.findAll(f -> true).size(),
                is(0));
    }

    @Test
    public void whenShopAddFood() {
        Calendar currentDate = Calendar.getInstance();

        double coef = 0.749;
        long sixtyDayInMillis = 1000L * 60 * 60 * 24 * 60;
        Calendar createDate = Calendar.getInstance();
        createDate.setTimeInMillis(currentDate.getTimeInMillis() - (int) (sixtyDayInMillis * (1 - coef)));
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTimeInMillis(currentDate.getTimeInMillis() + (int) (sixtyDayInMillis * coef));

        Food food = new Potatoes("food1", expiryDate, createDate, 100, 0.2);

        Store store = new Shop();
        store.add(food);
        assertThat(store.findAll(f -> true).get(0),
                is(new Potatoes("food1", expiryDate, createDate, 100, 0.2)));
        assertThat(store.findAll(f -> true).get(0).getPrice(),
                closeTo(100, 0.00001));
    }

    @Test
    public void whenShopNotAddFood() {
        Calendar currentDate = Calendar.getInstance();

        double coef = 0.0;
        long sixtyDayInMillis = 1000L * 60 * 60 * 24 * 60;
        Calendar createDate = Calendar.getInstance();
        createDate.setTimeInMillis(currentDate.getTimeInMillis() - (int) (sixtyDayInMillis * (1 - coef)));
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTimeInMillis(currentDate.getTimeInMillis() + (int) (sixtyDayInMillis * coef));

        Food food = new Potatoes("food1", expiryDate, createDate, 100, 0.2);

        Store store = new Shop();
        store.add(food);
        assertThat(store.findAll(f -> true).size(),
                is(0));
    }

    @Test
    public void whenShopAddFoodWhithDiscount() {
        Calendar currentDate = Calendar.getInstance();

        double coef = 0.249;
        long sixtyDayInMillis = 1000L * 60 * 60 * 24 * 60;
        Calendar createDate = Calendar.getInstance();
        createDate.setTimeInMillis(currentDate.getTimeInMillis() - (int) (sixtyDayInMillis * (1 - coef)));
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTimeInMillis(currentDate.getTimeInMillis() + (int) (sixtyDayInMillis * coef));

        Food food = new Potatoes("food1", expiryDate, createDate, 100, 0.2);

        Store store = new Shop();
        store.add(food);
        assertThat(store.findAll(f -> true).get(0).getPrice(),
                closeTo(80, 0.00001));
    }

    @Test
    public void whenTrashAddFood() {
        Calendar currentDate = Calendar.getInstance();

        double coef = -0.1;
        long sixtyDayInMillis = 1000L * 60 * 60 * 24 * 60;
        Calendar createDate = Calendar.getInstance();
        createDate.setTimeInMillis(currentDate.getTimeInMillis() - (int) (sixtyDayInMillis * (1 - coef)));
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTimeInMillis(currentDate.getTimeInMillis() + (int) (sixtyDayInMillis * coef));

        Food food = new Potatoes("food1", expiryDate, createDate, 100, 0.2);

        Store store = new Trash();
        store.add(food);
        assertThat(store.findAll(f -> true).get(0),
                is(new Potatoes("food1", expiryDate, createDate, 100, 0.2)));
    }

    @Test
    public void whenTrashNotAddFood() {
        Calendar currentDate = Calendar.getInstance();

        double coef = 0.001;
        long sixteenDayInMillis = 1000L * 60 * 60 * 24 * 60;
        Calendar createDate = Calendar.getInstance();
        createDate.setTimeInMillis(currentDate.getTimeInMillis() - (int) (sixteenDayInMillis * (1 - coef)));
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTimeInMillis(currentDate.getTimeInMillis() + (int) (sixteenDayInMillis * coef));

        Food food = new Potatoes("food1", expiryDate, createDate, 100, 0.2);

        Store store = new Trash();
        store.add(food);
        assertThat(store.findAll(f -> true).size(),
                is(0));
    }

}