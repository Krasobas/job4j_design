package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private int size;
    private int modCount;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            size++;
            modCount++;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        size++;
        modCount++;
    }

    public void addFirst(T value) {
        Node<T> node = new Node<>(value, head);
        head = node;
        size++;
        modCount++;
    }

    public boolean revert() {
        boolean rsl = false;
        if (size > 1) {
            Node<T> prev = null;
            Node<T> cur = head;
            Node<T> next;
            while (cur != null) {
                next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            head = prev;
            rsl = true;
        }
        return rsl;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> removed = head;
        head = removed.next;
        removed.next = null;
        size--;
        modCount++;
        return removed.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
