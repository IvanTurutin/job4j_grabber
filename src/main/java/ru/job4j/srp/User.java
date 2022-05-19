package ru.job4j.srp;

import java.util.Scanner;

/**
 * Модель данных User помимо хранения данных выполняет роль получения данных напрямую из консоли.
 * Ошибка заключается во-первых в том, что модель данных предназначена для хранения данных,
 * а не для получения их из какого либо источника. Происходит нарушение принципа единственной ответственности.
 * То есть метода inputName() тут быть не должно. Данные должны передаваться либо через сеттеры либо через конструктор.
 * Во-вторых ввод данных может осуществляться не только через консоль, но из файла, из БД. То есть идет
 * привязка к конкретной реализации ввода данных (нарушение принципа инверсии зависимости DIP).
 */

public class User {
    private String name;

    public String inputName() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
