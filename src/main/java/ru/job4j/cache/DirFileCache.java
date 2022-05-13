package ru.job4j.cache;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        Path path = Paths.get(cachingDir, key);
        StringBuilder sb = new StringBuilder();
        try (var scanner = new Scanner(path)) {
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}