package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.output.Output;
import ru.job4j.ood.srp.store.Store;

import java.util.*;
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
        List<Map<String, String>> prepared = new ArrayList<>();
        for (Employee employee : store.findBy(filter)) {
            Map<String, String> toAdd = new LinkedHashMap<>();
            toAdd.put("Name;", employee.getName());
            toAdd.put("Hired;", dateTimeParser.parse(employee.getHired()));
            toAdd.put("Fired;", dateTimeParser.parse(employee.getFired()));
            toAdd.put("Salary;", String.valueOf(employee.getSalary()));
            prepared.add(toAdd);
        }
        return output.print(prepared);
    }
}