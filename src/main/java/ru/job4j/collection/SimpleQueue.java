package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int sizeIn = 0;
    int sizeOut = 0;

    public T poll() {
        while (sizeIn > 0) {
            out.push(in.pop());
            sizeIn--;
            sizeOut++;
        }
        sizeOut--;
        return out.pop();
    }

    public void push(T value) {
        while (sizeOut > 0) {
            in.push(out.pop());
            sizeIn++;
            sizeOut--;
        }
        in.push(value);
        sizeIn++;
    }
}
