package ru.job4j.design.isp.menu;

import java.util.Scanner;
public class ScannerInput implements Input {

    private Scanner scanner;

    public ScannerInput() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }

}
