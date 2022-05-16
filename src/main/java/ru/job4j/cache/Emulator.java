package ru.job4j.cache;

import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.InvalidPathException;

public class Emulator {
    private String path;
    private String filename;

    public void init() {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        AbstractCache<String, String> dirFileCache = null;
        while (run) {
            this.showMenu();
            System.out.println("Select: ");
            int select = validateInt(scanner);
            switch (select) {
                case 0:
                    System.out.println("Input path: ");
                    path = validatePath(scanner);
                    dirFileCache = new DirFileCache(path);
                    break;
                case 1:
                    System.out.println("Input filename: ");
                    filename = validatePath(scanner);
                    break;
                case 2:
                    if (dirFileCache == null || filename == null) {
                        System.out.println("Input path and filename first.");
                        break;
                    }
                    System.out.printf("File: %s", filename);
                    System.out.println();
                    System.out.println(dirFileCache.get(filename));
                    break;
                case 3:
                    run = false;
                    break;
                default:
                    System.out.println("Wrong input, you should select: 0 .. 3");
                    break;
            }
        }
    }

    private int validateInt(Scanner scanner) {
        String input;
        int select = -1;
        boolean invalid = true;
        do {
            try {
                input = scanner.nextLine();
                select = Integer.parseInt(input);
                invalid = false;
            } catch (NumberFormatException e) {
                System.out.println("Wrong input, you should select: 0 .. 3");
            }
        } while (invalid);
        return select;
    }

    private String validatePath(Scanner scanner) {
        String input = "";
        boolean invalid = true;
        do {
            try {
                input = scanner.nextLine();
                Paths.get(input);
                invalid = false;
            } catch (InvalidPathException e) {
                System.out.println("Wrong input, you should input in Path format");
            }
        } while (invalid);
        return input;
    }

    private void showMenu() {
        System.out.println();
        System.out.println("=====================================");
        System.out.println("Menu:");
        System.out.printf("0. Input directory path. (current = %s)", path);
        System.out.println();
        System.out.printf("1. Input filename for read. (current = %s)", filename);
        System.out.println();
        System.out.println("2. Show file data.");
        System.out.println("3. Exit.");
        System.out.println("=====================================");

    }

    public static void main(String[] args) {
        new Emulator().init();
    }
}