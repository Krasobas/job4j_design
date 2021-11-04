package ru.job4j.collection.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleMapTest {

    @Test
    public void whenMultiplePutThenTrue() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(12345, "first"));
        assertTrue(map.put(67890, "second"));
    }

    @Test
    public void whenPutSameKeyTwiceThenFalse() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(12345, "first"));
        assertFalse(map.put(12345, "first"));
    }

    @Test
    public void whenGetWithGoodKeyThenValue() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(12345, "first"));
        assertThat(map.get(12345), is("first"));
    }

    @Test
    public void whenGetWithWrongKeyThenNull() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(12345, "first"));
        assertNull(map.get(67890));
    }

    @Test
    public void whenRemoveWithGoodKeyThenTrue() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(12345, "first"));
        assertTrue(map.remove(12345));
    }

    @Test
    public void whenRemoveWithWrongKeyThenFalse() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(12345, "first"));
        assertFalse(map.remove(67890));
    }

    @Test
    public void whenIteratorAndNextThenKey() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(12345, "first"));
        assertTrue(map.put(67890, "second"));
        Iterator<Integer> it = map.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(12345));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(67890));
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenHasNextIsFalseAndTryNextThenNSEE() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        Iterator<Integer> it = map.iterator();
        assertFalse(it.hasNext());
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenPutAfterIteratorAndNextThenCME() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(12345, "first"));
        Iterator<Integer> it = map.iterator();
        map.put(67890, "second");
        it.next();
    }

    @Test
    public void whenExpandThenSafeOldEntries() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(123456, "first");
        for (int i = 0; i < 10;) {
            int randomKey = (int) (Math.random() * (2147483647 - 147483647) + 147483647);
            if (map.put(randomKey, Integer.toString(randomKey))) {
                i++;
            }
        }
        assertThat(map.get(123456), is("first"));
    }
}