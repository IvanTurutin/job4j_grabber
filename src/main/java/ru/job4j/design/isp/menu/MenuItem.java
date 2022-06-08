package ru.job4j.design.isp.menu;

import java.util.List;

public interface MenuItem {

    String getName();

    List<MenuItem> getChildren();

    ActionDelegate getActionDelegate();

    boolean addChildren(MenuItem item);

}