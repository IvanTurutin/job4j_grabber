package ru.job4j.cache;

import java.util.Scanner;

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
            int select = scanner.nextInt();
            if (select < 0 || select >= 4) {
                System.out.println("Wrong input, you can select: 0 .. 3");
                continue;
            }
            switch (select) {
                case 0:
                    System.out.println("Input path: ");
                    path = scanner.next();
                    dirFileCache = new DirFileCache(path);
                    break;
                case 1:
                    System.out.println("Input filename: ");
                    filename = scanner.next();
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
                    break;
            }
        }
    }

    private void showMenu() {
        System.out.println("Menu:");
        System.out.printf("0. Input directory path. (current = %s)", path);
        System.out.println();
        System.out.printf("1. Input filename for read. (current = %s)", filename);
        System.out.println();
        System.out.println("2. Show file data.");
        System.out.println("3. Exit.");
    }

    public static void main(String[] args) {
        new Emulator().init();
    }
}
