package ru.job4j.ood.srp.output;


import lombok.Setter;

import java.util.List;
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
    public void append(List<String> data) {
        text.append(String.join(delimiter, data))
                .append(System.lineSeparator());
    }

    @Override
    public String print() {
        String result = text.toString();
        text.setLength(0);
        return result;
    }
}
