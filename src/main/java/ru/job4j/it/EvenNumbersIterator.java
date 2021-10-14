package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    /**
     * @return возвращает true, только если в массиве есть четные перед указателем
     */
    @Override
    public boolean hasNext() {
        while (index < data.length) {
            if (data[index] % 2 == 0) {
                return true;
            }
            index++;
        }
        return false;
    }

    /**
     * @return возвращают только четные числа
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }

}
