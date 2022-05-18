package ru.job4j.template;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.tdd.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;

public class GeneratorTest {

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenKeyMissing() {
        Generator gen = new StringGenerator();
        String str = "I am ${name}, Who are ${subject}?";
        Map<String, String> map = Map.of("name", "Ivan", "brand", "Nike");
        gen.produce(str, map);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenRedundandKey() {
        Generator gen = new StringGenerator();
        String str = "I am ${name}, Who are ${subject}?";
        Map<String, String> map = Map.of("name", "Ivan", "subject", "you", "brand", "Nike");
        gen.produce(str, map);
    }

    @Ignore
    @Test
    public void whenProduce() {
        Generator gen = new StringGenerator();
        String str = "I am ${name}, Who are ${subject}?";
        Map<String, String> map = Map.of("name", "Ivan", "subject", "you");
        String rst = gen.produce(str, map);
        assertTrue("I am Ivan, Who are you?".equals(rst));
    }
}