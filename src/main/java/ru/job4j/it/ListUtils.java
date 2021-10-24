package ru.job4j.it;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        list.listIterator(index).add(value);
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iter = list.listIterator(index);
        iter.next();
        iter.add(value);
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (filter.test(i.next())) {
                i.previous();
                i.remove();
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (filter.test(i.next())) {
                i.previous();
                i.set(value);
                i.next();
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        for (T element : elements) {
            ListIterator<T> src = list.listIterator();
            while (src.hasNext()) {
                if (src.next() == element) {
                    src.previous();
                    src.remove();
                }
            }
        }
    }
}