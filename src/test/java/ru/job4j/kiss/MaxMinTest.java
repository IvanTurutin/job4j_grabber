package ru.job4j.kiss;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class MaxMinTest {
    @Test
    public void whenMaxInteger() {
        List<Integer> integer = List.of(5, 4, 6, 3, 7, 2, 8, 1, 9);
        MaxMin maxMin = new MaxMin();
        int maxInt = maxMin.max(integer, Comparator.naturalOrder());
        assertThat(maxInt, is(9));
    }

    @Test
    public void whenMinInteger() {
        List<Integer> integer = List.of(5, 4, 6, 3, 7, 2, 8, 1, 9);
        MaxMin maxMin = new MaxMin();
        int minInt = maxMin.min(integer, Comparator.naturalOrder());
        assertThat(minInt, is(1));
    }

    @Test
    public void whenMaxString() {
        List<String> strings = List.of("String", "Char", "Boolean", "Long", "Double", "Float", "Byte", "int");
        MaxMin maxMin = new MaxMin();
        String max = maxMin.max(strings, Comparator.naturalOrder());
        assertThat(max, is("int"));
    }

    @Test
    public void whenMinString() {
        List<String> strings = List.of("String", "Char", "Boolean", "Long", "Double", "Float", "Byte", "int");
        MaxMin maxMin = new MaxMin();
        String min = maxMin.min(strings, Comparator.naturalOrder());
        assertThat(min, is("Boolean"));
    }
}