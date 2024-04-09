package ru.job4j.ood.srp.model;

import lombok.*;

import java.util.Calendar;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Employee {
    private String name;
    private Calendar hired;
    private Calendar fired;
    private double salary;
}