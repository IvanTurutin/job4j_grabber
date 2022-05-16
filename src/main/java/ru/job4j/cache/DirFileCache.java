package ru.job4j.cache;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String file = "";
        Path path = Paths.get(cachingDir, key);
        if (path.toFile().exists()) {
            try {
                file = Files.readString(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No such file. Please enter right path/filename.");
        }
        return file;
    }
}