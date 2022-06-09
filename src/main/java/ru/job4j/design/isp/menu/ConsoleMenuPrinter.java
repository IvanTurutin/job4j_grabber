package ru.job4j.design.isp.menu;

public class ConsoleMenuPrinter implements MenuPrinter {

    private Output output;

    public ConsoleMenuPrinter(Output output) {
        this.output = output;
    }

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo menuItemInfo : menu) {
            output.println(
                    "---".repeat((int) menuItemInfo.getNumber().chars().filter(c -> '.' == c).count() - 1)
                            .concat(menuItemInfo.getNumber())
                            .concat(menuItemInfo.getName())
            );
        }
    }

    public static void main(String[] args) {
        ActionDelegate action = System.out::println;

        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", action);
        menu.add(Menu.ROOT, "Покормить собаку", action);
        menu.add("Сходить в магазин", "Купить продукты", action);
        menu.add("Купить продукты", "Купить хлеб", action);
        menu.add("Купить продукты", "Купить молоко", action);
        menu.add("Сходить в магазин", "Купить шампунь", action);

        Output output = new ConsoleOutput();
        MenuPrinter menuPrinter = new ConsoleMenuPrinter(output);

        menuPrinter.print(menu);
    }
}
