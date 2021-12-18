package ru.job4j.io;

import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private static String directory;
    private static ArgsName validation(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Expected 3 arguments but you entered " + args.length);
        }
        ArgsName commands = ArgsName.of(args);
        boolean valid = Files.exists(Paths.get(commands.get("d")))
                && Files.isDirectory(Paths.get(commands.get("d")))
                && Pattern.matches("[a-z]+", commands.get("e"))
                && Pattern.matches(".+\\.zip", commands.get("o"));
        if (!valid) {
            throw new IllegalArgumentException("Illegal command was entered! Please use this template: -d=path -e=type -o=filename.zip");
        }
        return commands;
    }

    private static String cutPath(String path) {
        int start = path.indexOf(directory);
        return path.substring(start);
    }

    public static void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (Path source : sources) {
                packSingleFile(source.toFile(), zip);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, ZipOutputStream zip) {
        try {
            zip.putNextEntry(new ZipEntry(cutPath(source.getPath())));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
            zip.closeEntry();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
    java -jar pack.jar -d=/Users/vasilijkrasov/Desktop/projects/job4j_design -e=class -o=project.zip

    java -jar pack.jar - Это собранный jar.
    -d - directory - которую мы хотим архивировать.
    -e - exclude - исключить файлы с расширением class.
    -o - output - во что мы архивируем.
     */

    public static void main(String[] args) {
        ArgsName commands = validation(args);
        directory = Paths.get(commands.get("d")).getFileName().toString();
        try {
            List<Path> files = Search.search(Paths.get(commands.get("d")),
                    f -> !f.toFile().getName().endsWith(commands.get("e")));
            packFiles(files, Paths.get(commands.get("o")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
