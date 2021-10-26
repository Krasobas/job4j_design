package ru.job4j.collection.set;

import org.junit.Test;
import java.util.Iterator;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>(1);
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>(1);
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenMultiAddContains() {
        Set<Integer> set = new SimpleSet<>(3);
        assertTrue(set.add(1));
        assertTrue(set.add(2));
        assertFalse(set.add(1));
        assertTrue(set.add(3));
        assertFalse(set.add(2));
        assertTrue(set.contains(2));
        assertFalse(set.add(3));
        assertFalse(set.contains(4));
    }

    @Test
    public void whenAddAndIterator() {
        Set<Integer> set = new SimpleSet<>(2);
        set.add(1);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        it.next();
        assertThat(it.next(), is(2));
    }

}