package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringJoiner;

public class DirFileCache extends AbstractCache<String, String> {
    public static final String ERROR = "Ошибка: файл не найден";
    public static final String LOADING = "Загрузка файла";


    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    public String load(String key) {
        String rsl = "";
        try {
            Path path = Path.of(cachingDir, key);
            if (Files.exists(path)) {
                System.out.println(LOADING);
                rsl = Files.readString(Path.of(cachingDir, key));
            } else {
                System.out.println(ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}
