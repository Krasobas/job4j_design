package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(load(key)));
    }

    public V get(K key) {
        V entry;
        if (!cache.containsKey(key) || cache.get(key).get() == null) {
            entry = load(key);
            cache.put(key, new SoftReference<>(entry));
        } else {
            entry = cache.get(key).get();
        }
        return entry;
    }

    protected abstract V load(K key);

}
