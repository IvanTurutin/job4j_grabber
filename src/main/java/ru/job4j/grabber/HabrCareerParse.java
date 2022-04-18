package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HabrCareerParse {

    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer?page=", SOURCE_LINK);

    private String retrieveDescription(String link) throws IOException {
        Connection connection = Jsoup.connect(link);
        Document document = connection.get();
        Element descriptionElement = document.selectFirst(".style-ugc");
        return descriptionElement.text();
    }

    private void getPost(Element row) throws IOException {
            Element titleElement = row.select(".vacancy-card__title").first();
            Element linkElement = titleElement.child(0);
            String vacancyName = titleElement.text();
            Element vacancyCardDate = row.select(".vacancy-card__date").first();
            Element dateTime = vacancyCardDate.child(0);
            String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
            System.out.printf("%s %s %s%n", vacancyName, link, dateTime.attr("datetime"));
        /**
         * return new Post(vacancyName,
         *                     link,
         *                     retrieveDescription(link),
         *                     new HarbCareerDateTimeParser().parse(dateTime.attr("datetime"))
         *             );
         */

    }

    public static void main(String[] args) throws IOException {
        for (int i = 1; i < 6; i++) {
            String pageLink = String.format("%s%d", PAGE_LINK, i);
            Connection connection = Jsoup.connect(pageLink);
            Document document = connection.get();
            Elements rows = document.select(".vacancy-card__inner");
            rows.forEach(row -> {
                try {
                    new HabrCareerParse().getPost(row);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        System.out.println(
                new HabrCareerParse()
                        .retrieveDescription("https://career.habr.com/vacancies/1000100655")
        );
    }
}