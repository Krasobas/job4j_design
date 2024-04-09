package ru.job4j.ood.srp.report;

import lombok.Setter;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.output.Output;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

@Setter
public class AccountingDepartmentReport implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final Output output;
    private final CurrencyConverter converter;
    private Currency source;
    private Currency target;

    public AccountingDepartmentReport(Store store, DateTimeParser<Calendar> dateTimeParser, Output output, CurrencyConverter converter, Currency source, Currency target) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.output = output;
        this.converter = converter;
        this.source = source;
        this.target = target;
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
                            String.valueOf(converter.convert(source, employee.getSalary(), target))
                            )
            );
        }
        return output.print();
    }
}