package ru.job4j.design.isp.menu;

public class ConsoleOutput implements Output {

    @Override
    public void println(Object obj) {
        if (obj != null) {
            System.out.println(obj.toString());
        } else {
            System.out.println("null");
        }
    }

    @Override
    public void print(Object obj) {
        if (obj != null) {
            System.out.print(obj.toString());
        } else {
            System.out.print("null");
        }

    }

}
