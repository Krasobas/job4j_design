package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Analizy {
    public void unavailable(String source, String target) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            LinkedList<String> lines = in.lines()
                    .dropWhile(s -> s.startsWith("200") || s.startsWith("300"))
                    .collect(Collectors.toCollection(LinkedList::new));
            while (lines.size() != 0) {
                String cur = lines.poll();
                if (cur.startsWith("400") || cur.startsWith("500")) {
                    result.append(cur.substring(4));
                    result.append(";");
                    while (cur.startsWith("400") || cur.startsWith("500")) {
                        cur = lines.poll();
                    }
                    result.append(cur.substring(4));
                    result.append(System.lineSeparator());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            out.print(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Analizy().unavailable("./data/server.log", "./data/server_unavailable.log");
    }
}
