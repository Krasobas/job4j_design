package ru.job4j.ood.srp.output;


import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
public class DefaultOutput implements Output {
    private final StringBuilder text;
    private String delimiter;
    public DefaultOutput() {
        text = new StringBuilder();
        delimiter = " ";
    }

    public DefaultOutput(String delimiter) {
        text = new StringBuilder();
        this.delimiter = delimiter;
    }

    @Override
    public String print(List<Map<String, String>> data) {
        Set<String> header = data.stream().findFirst().orElse(Collections.emptyMap()).keySet();
        text.append(String.join(delimiter, header)).append(System.lineSeparator());
        for (Map<String, String> map : data) {
            String toAppend = header.stream().map(key -> map.getOrDefault(key, "")).collect(Collectors.joining(delimiter));
            text.append(toAppend).append(System.lineSeparator());
        }
        String result = text.toString();
        text.setLength(0);
        return result;
    }
}
