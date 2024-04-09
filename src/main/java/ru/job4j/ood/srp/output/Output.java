package ru.job4j.ood.srp.output;

import java.util.List;
import java.util.Map;

public interface Output {
    void append(List<String> data);
    String print();
}
