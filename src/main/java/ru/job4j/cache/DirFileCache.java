package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringJoiner;

public class DirFileCache extends AbstractCache<String, String> {
    public static final String ERROR = "Ошибка: файл не найден";

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        try {
            Path path = Path.of(cachingDir, key);
            if (Files.exists(path)) {
                Files.lines(path).forEach(joiner::add);
            } else {
                System.out.println(ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return joiner.toString();
    }
}
