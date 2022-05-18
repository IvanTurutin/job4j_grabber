package ru.job4j.template;

import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

public class GeneratorTest {

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenKeyMissing() {
        Generator gen = new StringGenerator();
        String str = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = Map.of("name", "Ivan", "brand", "Nike");
        gen.produce(str, map);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenRedundandKey() {
        Generator gen = new StringGenerator();
        String str = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = Map.of("name", "Ivan", "subject", "you", "brand", "Nike");
        gen.produce(str, map);
    }
}