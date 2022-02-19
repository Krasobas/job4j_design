package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {
    private String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
        load();
    }

    private void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            List<String> lines = read.lines()
                    .filter(s -> !s.isEmpty() && !s.startsWith("#"))
                    .collect(Collectors.toList());
            for (String line : lines) {
                if (line.startsWith("=") || !line.contains("=")) {
                    throw new IllegalArgumentException();
                }
            }
            Map<String, String> map = lines.stream()
                    .map(s -> s.split("="))
                    .filter(array -> array.length == 2)
                    .collect(Collectors.toMap(k -> k[0], v -> v[1]));
            values.putAll(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
