package ru.job4j.srp;

/**
 * Класс формирует отчет и может напечатать его.
 * Ошибка заключается в том, что класс помимо формирования отчета
 * осуществляет его печать. Что нарушает принцип единственной ответственности.
 * То есть метода printReport() тут быть не должно. Печать должна осуществляться посредством другого класса.
 */


public class Report {
    String rsl;

    public String report() {
        /*формирование отчета*/
        return rsl;
    }

    public void printReport() {
        /*печать отчета в консоль*/
    }
}
