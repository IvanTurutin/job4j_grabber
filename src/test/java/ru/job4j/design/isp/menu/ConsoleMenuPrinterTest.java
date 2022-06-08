package ru.job4j.design.isp.menu;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class ConsoleMenuPrinterTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenPrint() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);

        Output output = new BufferOutput();
        MenuPrinter menuPrinter = new ConsoleMenuPrinter(output);

        String ls = System.lineSeparator();
        StringBuilder sb = new StringBuilder();
        sb.append("1.Сходить в магазин").append(ls)
                .append("---1.1.Купить продукты").append(ls)
                .append("------1.1.1.Купить хлеб").append(ls);

        menuPrinter.print(menu);
        assertThat(output.toString(), is(sb.toString()));
    }
}