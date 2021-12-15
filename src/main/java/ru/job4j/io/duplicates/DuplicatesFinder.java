package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), visitor);
        Map<FileProperty, List<Path>> duplicates = visitor.getDuplicates();
        for (Map.Entry<FileProperty, List<Path>> entry : duplicates.entrySet()) {
            System.out.printf("File %s has %d copies:%n", entry.getKey().getName(), entry.getValue().size());
            entry.getValue().forEach(System.out::println);
        }
    }
}
