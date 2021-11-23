package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> rsl = null;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            rsl = in.lines()
                    .map(s -> s.split(" "))
                    .filter(array -> array[array.length - 2].equals("404"))
                    .map(array -> String.join(" ", array))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void main(String[] args) {
        filter("log.txt")
                .forEach(System.out::println);
    }
}
