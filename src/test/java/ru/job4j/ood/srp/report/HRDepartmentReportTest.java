package ru.job4j.ood.srp.report;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.output.DefaultOutput;
import ru.job4j.ood.srp.output.JsonOutput;
import ru.job4j.ood.srp.output.Output;
import ru.job4j.ood.srp.output.XMLOutput;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

class HRDepartmentReportTest {
    @Test
    public void whenTwoEmployeesGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 300);

        Output output = new DefaultOutput();
        store.add(worker1);
        store.add(worker2);
        Report engine = new HRDepartmentReport(store, output);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(worker2.getSalary())
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(worker1.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenJsonOutputGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 300);

        Output output = new JsonOutput();
        store.add(worker1);
        store.add(worker2);
        Report engine = new HRDepartmentReport(store, output);
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
        assertThat(engine.generate(employee -> true)).isEqualTo(expected);
    }

    @Test
    public void whenXmlOutputGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 300);

        Output output = new XMLOutput("employees", "employee");
        store.add(worker1);
        store.add(worker2);
        Report engine = new HRDepartmentReport(store, output);
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
        assertThat(engine.generate(employee -> true)).isEqualToIgnoringNewLines(expected);
    }
}