package ru.job4j.design.lsp;

import org.junit.Test;
import ru.job4j.design.lsp.shopstore.*;

import java.util.Calendar;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

public class StoreTest {

    @Test
    public void whenCoeffIsZeroPointOneFive() {

        Calendar currentDate = Calendar.getInstance();

        Store store = new Shop();

        double coef = 0.15;
        var sixtyDayInMillis = 1000L * 60L * 60L * 24L * 60L;
        Calendar createDate = Calendar.getInstance();
        createDate.setTimeInMillis(currentDate.getTimeInMillis() - (long) (sixtyDayInMillis * (1 - coef)));
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTimeInMillis(currentDate.getTimeInMillis() + (long) (sixtyDayInMillis * coef));

        Food food = new Potatoes("food1", expiryDate, createDate, 100, 0.2);

        assertThat(store.getExpirationPercentage(food), closeTo(coef, 0.00001));
    }

    @Test
    public void whenWarehouseAddFood() {
        Calendar currentDate = Calendar.getInstance();

        double coef = 0.751;
        var sixtyDayInMillis = 1000L * 60L * 60L * 24L * 60L;
        Calendar createDate = Calendar.getInstance();
        createDate.setTimeInMillis(currentDate.getTimeInMillis() - (long) (sixtyDayInMillis * (1 - coef)));
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTimeInMillis(currentDate.getTimeInMillis() + (long) (sixtyDayInMillis * coef));

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
        var sixtyDayInMillis = 1000L * 60L * 60L * 24L * 60L;
        Calendar createDate = Calendar.getInstance();
        createDate.setTimeInMillis(currentDate.getTimeInMillis() - (long) (sixtyDayInMillis * (1 - coef)));
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTimeInMillis(currentDate.getTimeInMillis() + (long) (sixtyDayInMillis * coef));

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
        var sixtyDayInMillis = 1000L * 60L * 60L * 24L * 60L;
        Calendar createDate = Calendar.getInstance();
        createDate.setTimeInMillis(currentDate.getTimeInMillis() - (long) (sixtyDayInMillis * (1 - coef)));
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTimeInMillis(currentDate.getTimeInMillis() + (long) (sixtyDayInMillis * coef));

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
        var sixtyDayInMillis = 1000L * 60L * 60L * 24L * 60L;
        Calendar createDate = Calendar.getInstance();
        createDate.setTimeInMillis(currentDate.getTimeInMillis() - (long) (sixtyDayInMillis * (1 - coef)));
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTimeInMillis(currentDate.getTimeInMillis() + (long) (sixtyDayInMillis * coef));

        Food food = new Potatoes("food1", expiryDate, createDate, 100, 0.2);

        Store store = new Shop();
        store.add(food);
        assertThat(store.findAll(f -> true).size(),
                is(0));
    }

    @Test
    public void whenShopAddFoodWithDiscount() {
        Calendar currentDate = Calendar.getInstance();

        double coef = 0.249;
        var sixtyDayInMillis = 1000L * 60L * 60L * 24L * 60L;
        Calendar createDate = Calendar.getInstance();
        createDate.setTimeInMillis(currentDate.getTimeInMillis() - (long) (sixtyDayInMillis * (1 - coef)));
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTimeInMillis(currentDate.getTimeInMillis() + (long) (sixtyDayInMillis * coef));

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
        var sixtyDayInMillis = 1000L * 60L * 60L * 24L * 60L;
        Calendar createDate = Calendar.getInstance();
        createDate.setTimeInMillis(currentDate.getTimeInMillis() - (long) (sixtyDayInMillis * (1 - coef)));
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTimeInMillis(currentDate.getTimeInMillis() + (long) (sixtyDayInMillis * coef));

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
        var sixtyDayInMillis = 1000L * 60L * 60L * 24L * 60L;
        Calendar createDate = Calendar.getInstance();
        createDate.setTimeInMillis(currentDate.getTimeInMillis() - (long) (sixtyDayInMillis * (1 - coef)));
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTimeInMillis(currentDate.getTimeInMillis() + (long) (sixtyDayInMillis * coef));

        Food food = new Potatoes("food1", expiryDate, createDate, 100, 0.2);

        Store store = new Trash();
        store.add(food);
        assertThat(store.findAll(f -> true).size(),
                is(0));
    }

}