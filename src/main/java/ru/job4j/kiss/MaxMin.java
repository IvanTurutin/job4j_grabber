package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;


public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return minMax(value, comparator.reversed());
    }
    /**
     * Вариант с использованием сортировки (медленнее работает, т.к. список сначала сортируется,
     * а это медленнее (O(n*Log(n))), чем прямой поиск min/max (O(n)))
     *      public <T> T min(List<T> value, Comparator<T> comparator) {
     *      List<T> copyOfValue = new ArrayList<>(value);
     *      copyOfValue.sort(comparator);
     *      return copyOfValue.get(0);
     *      }
     */
    public <T> T min(List<T> value, Comparator<T> comparator) {
        return minMax(value, comparator);
    }

    private <T> T minMax(List<T> value, Comparator<T> comparator) {
        T target = value.get(0);
        for (T tmp : value) {
            if (comparator.compare(target, tmp) >= 0) {
                target = tmp;
            }
        }
        return target;
    }
}