package ru.job4j.grabber.utils;

import java.time.LocalDateTime;
import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;

public class HarbCareerDateTimeParser implements DateTimeParser {

    private LocalDateTime parsedDate;

    public HarbCareerDateTimeParser() {
    }

    @Override
    public LocalDateTime parse(String parse) {
        parsedDate = LocalDateTime.parse(parse, ISO_DATE_TIME);
        return parsedDate;
    }

    public LocalDateTime getParsedDate() {
        return parsedDate;
    }

    public static void main(String[] args) {
        String dateForParseTest = "2022-04-16T10:02:44+03:00";
        HarbCareerDateTimeParser harbCareerDateTimeParser = new HarbCareerDateTimeParser();
        LocalDateTime parsedDateTest = harbCareerDateTimeParser.parse(dateForParseTest);
        System.out.println(parsedDateTest);
        LocalDateTime parsedDateTestGet = harbCareerDateTimeParser.getParsedDate();
        System.out.println(parsedDateTestGet);
    }
}
