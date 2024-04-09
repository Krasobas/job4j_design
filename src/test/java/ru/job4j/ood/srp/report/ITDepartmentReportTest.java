package ru.job4j.ood.srp.report;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.output.DefaultOutput;
import ru.job4j.ood.srp.output.Output;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

class ITDepartmentReportTest {
    @Test
    public void whenDotCommaDelimiterGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        String delimiter = ";";
        Output output = new DefaultOutput(delimiter);
        store.add(worker);
        Report engine = new ITDepartmentReport(store, parser, output);
        StringBuilder expected = new StringBuilder()
                .append("Name;Hired;Fired;Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(delimiter)
                .append(parser.parse(worker.getHired())).append(delimiter)
                .append(parser.parse(worker.getFired())).append(delimiter)
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }
}