package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    private String[] validation(String arg) {
        if (!Pattern.matches("-[a-zA-Z]+=.+", arg)) {
            throw new IllegalArgumentException("Illegal command was entered! Use this template: -key=value");
        }
        String[] temp = arg.substring(1).split("=");
        if (temp.length != 2) {
            throw new IllegalArgumentException("Illegal command was entered! Use = only to separate key and value");
        }
        return temp;
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No any argument was entered!");
        }
        for (String arg : args) {
            String[] temp = validation(arg);
            values.put(temp[0], temp[1]);
        }

    }

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Illegal key!");
        }
        return values.get(key);
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public int size() {
        return values.size();
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
