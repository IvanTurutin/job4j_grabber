package ru.job4j.grabber;

import ru.job4j.grabber.utils.HarbCareerDateTimeParser;
import ru.job4j.quartz.AlertRabbit;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {

    private Connection cnn;
    String table = "post";

    public PsqlStore(Properties cfg) throws SQLException {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

            cnn = DriverManager.getConnection(
                    cfg.getProperty("url"),
                    cfg.getProperty("username"),
                    cfg.getProperty("password")
            );
        /* cnn = DriverManager.getConnection(...); */
    }

    @Override
    public void save(Post post) {
        String query = String.format("insert into %s (name, text, link, created) values (?, ?, ?, ?)", table);
        try (PreparedStatement ps = cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getDescription());
            ps.setString(3, post.getLink());
            ps.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            ps.execute();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    post.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        String query = String.format("select * from %s", table);
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    posts.add(getPost(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    private Post getPost(ResultSet resultSet) throws SQLException {
        return new Post(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("link"),
                resultSet.getString("text"),
                resultSet.getTimestamp("created").toLocalDateTime()
        );
    }

    @Override
    public Post findById(int id) {
        Post post = null;
        String query = String.format("select * from %s where id = ?", table);
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    post = getPost(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }

    public static void main(String[] args) {
        Properties config;
        try (InputStream in = PsqlStore.class.getClassLoader().getResourceAsStream("grabber.properties")) {
            config = new Properties();
            config.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        Parse parse = new HabrCareerParse(new HarbCareerDateTimeParser());
        String pageLink = "https://career.habr.com/vacancies/java_developer?page=";
        List<Post> posts = new ArrayList<>(parse.list(pageLink));
        try (PsqlStore psqlStore = new PsqlStore(config)) {
            posts.forEach(psqlStore::save);
            psqlStore.getAll().forEach(post -> System.out.println(post.getTitle()));
            System.out.println(psqlStore.findById(1).getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}