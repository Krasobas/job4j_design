package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int sizeIn = 0;
    private int sizeOut = 0;

    public T poll() {
        sizeOut += reverse(out, in, sizeIn);
        if (sizeOut == 0) {
            throw new NoSuchElementException();
        }
        sizeIn = 0;
        sizeOut--;
        return out.pop();
    }

    public void push(T value) {
        sizeIn += reverse(in, out, sizeOut);
        sizeOut = 0;
        in.push(value);
        sizeIn++;
    }

    private int reverse(SimpleStack<T> dest, SimpleStack<T> src, int sizeSrc) {
        int count = 0;
        while (sizeSrc > 0) {
            dest.push(src.pop());
            sizeSrc--;
            count++;
        }
        return count;
    }
}
