package ru.job4j.design.isp.menu;

public class ConsoleOutput implements Output {

    @Override
    public void println(Object obj) {
        System.out.println(obj.toString());
    }

    @Override
    public void print(Object obj) {
        System.out.print(obj.toString());
    }

}
