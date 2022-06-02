package ru.job4j.design.isp;

/**
 *	Интерфейс описывает часы. В интерфейсе есть методы показать вермя, включить подсветку, установить
 *	будильник.
 *	Ошибка заключается в том, что не все часы будут иметь эти функции. Для исправления ошибки необходимо
 *	разбить интерфейс на 3 интерфейса каждый со своим методом.
 */

public interface Watch {
    String showTime();
    void turnBacklight();
    void setAlarm(String time);
}