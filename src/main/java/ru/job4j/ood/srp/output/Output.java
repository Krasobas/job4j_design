package ru.job4j.ood.srp.output;

import java.util.List;
import java.util.Map;

public interface Output {
    String print(List<Map<String, String>> data);
}
