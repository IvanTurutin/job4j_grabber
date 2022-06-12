package ru.job4j.design.isp.menu;

public class ToDoList {

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        ActionDelegate action = System.out::println;
        Input scanner = new ScannerInput();
        Output output = new ConsoleOutput();
        MenuPrinter consolePrinter = new ConsoleMenuPrinter(output);
        new ToDoList().init(scanner, menu, action, consolePrinter);
    }

    public void init(Input scanner, Menu menu, ActionDelegate action, MenuPrinter consolePrinter) {
        boolean run = true;
        while (run) {
            this.showMenu();
            System.out.println("Select: ");
            int select = validateInt(scanner);
            switch (select) {
                case 0:
                    selectRootChild(scanner, menu, action);
                    break;
                case 1:
                    consolePrinter.print(menu);
                    break;
                case 2:
                    run = false;
                    break;
                default:
                    System.out.println("Wrong input, you should select: 0 .. 2");
                    break;
            }
        }
    }

    private void selectRootChild(Input scanner, Menu menu, ActionDelegate action) {
        System.out.println("Is it a root item? (y/n)");
        String input;
        boolean invalid = true;
        do {
            input = scanner.nextLine();
            if ("y".equals(input) || "Y".equals(input)) {
                inputItem(scanner, menu, action, Menu.ROOT);
                invalid = false;
            } else if ("n".equals(input) || "N".equals(input)) {
                System.out.println("Enter the parent's name.");
                String parentName = scanner.nextLine();
                inputItem(scanner, menu, action, parentName);
                invalid = false;
            } else {
                System.out.println("You should enter \"y\" if yes, or \"n\" if no.");
            }
        } while (invalid);
    }

    private void inputItem(Input scanner, Menu menu, ActionDelegate action, String parentName) {
        System.out.println("Enter item name.");
        String itemName = scanner.nextLine();
        if (menu.add(parentName, itemName, action)) {
            System.out.println("Item added successfully");
        } else {
            System.out.println("Item didn't add");
        }
    }

    private int validateInt(Input scanner) {
        String input;
        int select = -1;
        boolean invalid = true;
        do {
            try {
                input = scanner.nextLine();
                select = Integer.parseInt(input);
                invalid = false;
            } catch (NumberFormatException e) {
                System.out.println("Wrong input, you should select: 0 .. 2");
            }
        } while (invalid);
        return select;
    }

    private void showMenu() {
        System.out.println();
        System.out.println("=====================================");
        System.out.println("Menu:");
        System.out.println("0. Enter new item.");
        System.out.println("1. Show todo list.");
        System.out.println("2. Exit.");
        System.out.println("=====================================");

    }

}