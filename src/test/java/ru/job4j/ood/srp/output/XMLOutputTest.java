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
                        <Salary>300.0</Salary>
                    </employee>
                    <employee>
                        <Name>Ivan</Name>
                        <Salary>100.0</Salary>
                    </employee>
                </employees>
                """;
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
        Output output = new XMLOutput("employees", "employee");
        assertThat(output.print(data)).isEqualToIgnoringNewLines(expected);
    }

}