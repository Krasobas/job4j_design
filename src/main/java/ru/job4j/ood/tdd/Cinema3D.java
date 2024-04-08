package ru.job4j.ood.tdd;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {
    private final Set<Session> sessions = new HashSet<>();
    @Override
    public List<Session> find(Predicate<Session> filter) {
        return sessions.stream().filter(filter).toList();
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        if (row <= 0 || column <= 0 || account == null || date == null) {
            throw new IllegalArgumentException();
        }
        return new Ticket3D();
    }

    @Override
    public void add(Session session) {
        sessions.add(session);
    }
}
