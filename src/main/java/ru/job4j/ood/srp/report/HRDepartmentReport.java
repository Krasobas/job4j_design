package ru.job4j.ood.srp.report;

import lombok.Setter;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.output.Output;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
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
        output.append(List.of("Name;", "Salary;"));
        List<Employee> employees = store.findBy(filter);
        employees.sort(sort);
        for (Employee employee : employees) {
            output.append(
                    List.of(
                            employee.getName(),
                            String.valueOf(employee.getSalary())
                            )
            );
        }
        return output.print();
    }
}