package ru.job4j.ood.srp.report;

import lombok.Setter;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.output.Output;
import ru.job4j.ood.srp.store.Store;

import java.util.*;
import java.util.function.Predicate;

@Setter
public class HRDepartmentReport implements Report {

    private final Store store;
    private final Output output;
    private final Comparator<Employee> sort;

    public HRDepartmentReport(Store store, Output output) {
        this.store = store;
        this.output = output;
        this.sort = Comparator.comparing(Employee::getSalary).reversed();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        employees.sort(sort);
        List<Map<String, String>> prepared = new ArrayList<>();
        for (Employee employee : employees) {
            Map<String, String> toAdd = new LinkedHashMap<>();
            toAdd.put("Name;", employee.getName());
            toAdd.put("Salary;", String.valueOf(employee.getSalary()));
            prepared.add(toAdd);
        }
        return output.print(prepared);
    }
}