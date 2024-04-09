package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.output.Output;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportEngine implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final Output output;

    public ReportEngine(Store store, DateTimeParser<Calendar> dateTimeParser, Output output) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.output = output;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        output.append(List.of("Name;", "Hired;", "Fired;", "Salary;"));
        for (Employee employee : store.findBy(filter)) {
            output.append(
                    List.of(
                            employee.getName(),
                            dateTimeParser.parse(employee.getHired()),
                            dateTimeParser.parse(employee.getFired()),
                            String.valueOf(employee.getSalary())
                            )
            );
        }
        return output.print();
    }
}