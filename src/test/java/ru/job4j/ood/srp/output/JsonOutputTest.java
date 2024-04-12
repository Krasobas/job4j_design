package ru.job4j.ood.srp.output;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class JsonOutputTest {

    @Test
    void whenListWith2Items() {
        String expected = """
                [
                  {
                    "Name;": "Petr",
                    "Salary;": "300.0"
                  },
                  {
                    "Name;": "Ivan",
                    "Salary;": "100.0"
                  }
                ]""";
        List<Map<String, String>> data = List.of(
                new LinkedHashMap<>(Map.of(
                        "Name;", "Petr",
                        "Salary;", "300.0"
                )),
                new LinkedHashMap<>(Map.of(
                        "Name;", "Ivan",
                        "Salary;", "100.0"
                ))
        );
        Output output = new JsonOutput();
        assertThat(output.print(data)).isEqualTo(expected);
    }

}