package ru.job4j.collection.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int hash = hash(key.hashCode());
        int index;
        boolean rsl;
        if (count == capacity * LOAD_FACTOR) {
            expand();
        }
        index = indexFor(hash);
        rsl = table[index] == null;
        if (rsl) {
            table[index] = new MapEntry<>(hash, key, value);
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                int index = indexFor(entry.hash);
                if (newTable[index] == null) {
                    newTable[index] = entry;
                }
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key.hashCode()));
        return table[index] == null ? null : table[index].value;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(hash(key.hashCode()));
        boolean rsl = table[index] != null;
        if (rsl) {
            table[index] = null;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int point = 0;
            private int expectedModCount = modCount;
            private int length = 0;
            @Override
            public boolean hasNext() {
                return point < count;
            }

            @Override
            public K next() {
                K rsl = null;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (length < table.length) {
                    if (table[length] != null) {
                        rsl = table[length++].key;
                        point++;
                        break;
                    }
                    length++;
                }
                return rsl;
            }
        };
    }

    private static class MapEntry<K, V> {

        int hash;
        K key;
        V value;

        public MapEntry(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

    }

}
