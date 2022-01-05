package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

public class CSVReader {
    private ArgsName argsName;

    public CSVReader(String[] args) {
        this.argsName = validation(args);
    }

    private ArgsName validation(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        if (argsName.size() != 4) {
            throw new IllegalArgumentException("Expected 4 arguments but you entered " + argsName.size());
        }
        String path = argsName.get("path");
        if (!Pattern.matches(".+\\.csv", path)) {
            throw new IllegalArgumentException("The source file should be an CSV!");
        }
        if (!Files.exists(Paths.get(path))) {
            throw new IllegalArgumentException("The source file doesn't exist!");
        }
        String out = argsName.get("out");
        if (!out.equals("stdout") && !Pattern.matches(".+\\.[a-z]+", out)) {
            throw new IllegalArgumentException("The -out key should be stdout or a path to a file!");
        }
        return argsName;
    }

    private String filter(Scanner scanner) {
        scanner.useDelimiter(argsName.get("delimiter") + "|\n");
        List<String> header = Arrays.asList(scanner.nextLine().split(";"));
        List<String> filters = new ArrayList<>(Arrays.asList(argsName.get("filter").split(",")));
        filters.retainAll(header);
        ArrayList<Integer> columns = new ArrayList<>();
        StringBuilder content = new StringBuilder();
        int count = 0;
        for (int i = 0; i < header.size(); i++) {
            if (filters.contains(header.get(i))) {
                content.append(header.get(i));
                columns.add(i);
                count++;
                if (count < filters.size()) {
                    content.append(';');
                }
                if (count == filters.size()) {
                    content.append('\n');
                }
            }
        }
        int index = 0;
        count = 0;
        while (scanner.hasNext()) {
            String temp = scanner.next();
            if (index == header.size()) {
                index = 0;
            }
            if (columns.contains(index)) {
                content.append(temp);
                count++;
                if (count < columns.size()) {
                    content.append(';');
                }
                if (count == columns.size()) {
                    content.append('\n');
                    count = 0;
                }
            }
            index++;
        }
        return content.toString();
    }

    public void handle() {
        String rsl = "";
        try (Scanner scanner = new Scanner(Paths.get(argsName.get("path")))) {
            rsl = filter(scanner);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (argsName.get("out").equals("stdout")) {
            System.out.print(rsl);
        } else {
            try (PrintWriter writer = new PrintWriter(new FileWriter(argsName.get("out")))) {
                writer.print(rsl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws Exception {
        File file = new File("data/test.csv");
        File target = new File("data/result.csv");
        String[] testArgs = new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=last_name,education,name"
        };
        new CSVReader(testArgs).handle();
    }
}