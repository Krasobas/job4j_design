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
        list.listIterator(index + 1).add(value);
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iter = list.listIterator();
        while (iter.hasNext()) {
            if (filter.test(iter.next())) {
                iter.remove();
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iter = list.listIterator();
        while (iter.hasNext()) {
            if (filter.test(iter.next())) {
                iter.set(value);
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        if (list.size() > 0 && elements.size() > 0) {
            ListIterator<T> src = list.listIterator();
            int index = 0;
            T temp = elements.get(index++);
            while (src.hasNext()) {
                if (temp == src.next()) {
                    src.remove();
                }
                if (!src.hasNext() && index < elements.size()) {
                    src = list.listIterator();
                    temp = elements.get(index++);
                }
            }
        }
    }
}