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
                    "Hired;": "12:04:2024 08:26",
                    "Fired;": "12:04:2024 08:26",
                    "Salary;": "300.0"
                  },
                  {
                    "Name;": "Ivan",
                    "Hired;": "12:04:2024 08:26",
                    "Fired;": "12:04:2024 08:26",
                    "Salary;": "100.0"
                  }
                ]""";
        Map<String, String> worker1 = new LinkedHashMap<>();
        worker1.put("Name;", "Petr");
        worker1.put("Hired;", "12:04:2024 08:26");
        worker1.put("Fired;", "12:04:2024 08:26");
        worker1.put("Salary;", "300.0");
        Map<String, String> worker2 = new LinkedHashMap<>();
        worker2.put("Name;", "Ivan");
        worker2.put("Hired;", "12:04:2024 08:26");
        worker2.put("Fired;", "12:04:2024 08:26");
        worker2.put("Salary;", "100.0");
        List<Map<String, String>> data = List.of(worker1, worker2);
        Output output = new JsonOutput();
        assertThat(output.print(data)).isEqualTo(expected);
    }

}