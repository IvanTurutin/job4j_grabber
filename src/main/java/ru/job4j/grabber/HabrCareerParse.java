package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HarbCareerDateTimeParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HabrCareerParse implements Parse {

    private final DateTimeParser dateTimeParser;

    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer?page=", SOURCE_LINK);

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    private String retrieveDescription(String link) throws IOException {
        Connection connection = Jsoup.connect(link);
        Document document = connection.get();
        Element descriptionElement = document.selectFirst(".style-ugc");
        return descriptionElement.text();
    }

    private Post getPost(Element row) throws IOException {
            Element titleElement = row.select(".vacancy-card__title").first();
            Element linkElement = titleElement.child(0);
            String vacancyName = titleElement.text();
            Element vacancyCardDate = row.select(".vacancy-card__date").first();
            Element dateTime = vacancyCardDate.child(0);
            String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
        return new Post(vacancyName,
                link,
                retrieveDescription(link),
                dateTimeParser.parse(dateTime.attr("datetime"))
        );
    }

    @Override
    public List<Post> list(String link) {
        List<Post> posts = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            String pageLink = String.format("%s%d", link, i);
            Connection connection = Jsoup.connect(pageLink);
            try {
                Document document = connection.get();
                Elements rows = document.select(".vacancy-card__inner");
                for (Element e : rows) {
                    posts.add(getPost(e));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return posts;
    }

    public static void main(String[] args) {
        Parse habrCareerParse = new HabrCareerParse(new HarbCareerDateTimeParser());
        String pageLink = PAGE_LINK;
        List<Post> posts = new ArrayList<>(habrCareerParse.list(pageLink));
        posts.forEach(post -> System.out.println(post.getTitle()));
    }

}