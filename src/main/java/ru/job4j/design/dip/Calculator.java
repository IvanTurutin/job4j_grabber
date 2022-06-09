package ru.job4j.design.dip;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *  Класс производит вычисленние суммы двух чисел и результаты складывает в хранилище.
 *  Тут допущены три ошибки связанные с наружением принципа DIP.
 *  1. Ввод аргументов для суммы осуществляется через конкретную реализацию (Scanner).
 *  2. Вывод результата суммы осуществляется через конкретную реализацию (System.out).
 *  3. Хранение результатов так же осуществляется в конкретной реализации хранилища (ArrayList).
 *  Все эти три реализации необходимо заменить на интерфейсы для возможности легкой смены реализации.
 */
public class Calculator {

    private int arg1;
    private int arg2;
    private List<Integer> resultsStore = new ArrayList<>();

    public void inputArgs() {
        Scanner scanner = new Scanner(System.in);
        arg1 = scanner.nextInt();
        arg2 = scanner.nextInt();
    }

    public void sumResult() {
        int sum = arg1 + arg2;
        System.out.println(sum);
        resultsStore.add(sum);
    }
}
