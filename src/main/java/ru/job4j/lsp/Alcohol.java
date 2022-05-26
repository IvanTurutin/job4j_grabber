package ru.job4j.lsp;


import java.util.Calendar;

/**
 *	Класс Alcohol осуществляет продажу алкогольных товаров.
 *	Алкоголь запрещено продавать с 22 до 11 часов.
 *	метод toSell() проверяет, разрешено ли в данное время продажа данного продукта.
 *	Ошибка заключается в том, что при расширении класса Alcohol классом Beer, было
 *	ужесточено предусловие в методе toSell(). Мы ожидаем, что пиво можно купить
 *	с 11 до 22 часов, как и весь алкоголь, но это оказывается не так.
 */


public class Alcohol {

    public Alcohol() {
    }

    public void toSell(int hour) {
        boolean rst = false;
        if (hour < 11 || hour > 21) {
            throw new IllegalArgumentException("В это время продавать алкоголь нельзя!");
        }
        /*процесс продажи товара*/
    }
}

class Beer extends Alcohol {

    @Override
    public void toSell(int hour) {
        boolean rst = false;
        if (hour < 11 || hour > 21) {
            throw new IllegalArgumentException("В это время продавать алкоголь нельзя!");
        }
        if (hour == 13) {
            throw new IllegalArgumentException("В это время продавать алкоголь нельзя!");
        }
        /*процесс продажи товара*/
    }
}

