package ru.job4j.grabber.utils;

import java.time.LocalDateTime;
import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

public class HarbCareerDateTimeParser implements DateTimeParser {

    @Override
    public LocalDateTime parse(String parse) {
        return LocalDateTime.parse(parse, ISO_DATE_TIME);
    }

    public static void main(String[] args) {
        String dateForParseTest = "2022-04-16T10:02:44+03:00";
        HarbCareerDateTimeParser harbCareerDateTimeParser = new HarbCareerDateTimeParser();
        LocalDateTime parsedDateTest = harbCareerDateTimeParser.parse(dateForParseTest);
        System.out.println(parsedDateTest);
    }
}
