package ru.job4j.ood.srp.output;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class XMLOutputTest {

    @Test
    void whenListWith2Items() {
        String expected = """
                <?xml version="1.0" encoding="UTF-8" standalone="no"?>
                <employees>
                    <employee>
                        <Name>Petr</Name>
                        <Hired>12:04:2024 08:26</Hired>
                        <Fired>12:04:2024 08:26</Fired>
                        <Salary>300.0</Salary>
                    </employee>
                    <employee>
                        <Name>Ivan</Name>
                        <Hired>12:04:2024 08:26</Hired>
                        <Fired>12:04:2024 08:26</Fired>
                        <Salary>100.0</Salary>
                    </employee>
                </employees>
                """;
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
        Output output = new XMLOutput("employees", "employee");
        assertThat(output.print(data)).isEqualToIgnoringNewLines(expected);
    }

}