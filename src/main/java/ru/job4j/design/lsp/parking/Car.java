package ru.job4j.design.lsp.parking;

public interface Car {

/**
 * Реализации интерфейса Car должны содержать название машины и ее размер
 * (1 для легкового автомобиля, 2 и более для грузового).
 */

    int getSize();
}
