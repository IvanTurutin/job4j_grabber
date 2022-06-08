package ru.job4j.design.isp.menu;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertEquals(
                new Menu.MenuItemInfo(
                        "Сходить в магазин", List.of("Купить продукты"), STUB_ACTION, "1."
                ),
                menu.select("Сходить в магазин").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Купить продукты", List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."
                ),
                menu.select("Купить продукты").get()
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Покормить собаку", List.of(), STUB_ACTION, "2."
                ),
                menu.select("Покормить собаку").get()
        );
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenSelectIsPresent() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);

        assertThat(menu.select("Купить хлеб").get(),
                is(new Menu.MenuItemInfo(
                        "Купить хлеб",
                        new ArrayList<String>(),
                        STUB_ACTION,
                        "1.1.1.")));
    }

    @Test
    public void whenSelectIsEmpty() {
        Menu menu = new SimpleMenu();
        assertTrue(menu.select("Купить хлеб").isEmpty());
    }
}