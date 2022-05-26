package ru.job4j.lsp;

/**
 *  Класс TransferCommission производит расчет комиссии за банковский перевод.
 *  При этом по достижении определенной суммы комиссии ее размер не увеличивается (постусловие).
 *  Произойдет нарушение принципа подстановки Лисков если в классе наследнике RobberyTransferCommission
 *  убрать данное постусловие, то есть ослабить его.
 *  В случае использования класса-наследника мы ожидаем, что комиссия за перевод не превысит 100, но
 *  данное условие не проверяется, поэтому класс работает не так как мы рассчитываем.
 */

public class TransferCommission {

    private double percentage = 0.01;

    public double calculateCommission(Double amount) {
        double commission = amount * percentage;
        if (commission > 100) {
            commission = 100;
        }
        return commission;
    }
}

class RobberyTransferCommission extends TransferCommission {

    private double percentage = 0.01;

    public double calculateCommission(Double amount) {
        double commission = amount * percentage;
        return commission;
    }

}
