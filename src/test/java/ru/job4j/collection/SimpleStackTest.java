package ru.job4j.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleStackTest {
    SimpleStack<Integer> linked;

    @Before
    public void setUp() {
        linked = new SimpleStack<>();
    }

    @Test
    public void whenMultiPushAndPop() {
        linked.push(1);
        linked.push(2);
        linked.push(3);
        assertThat(linked.pop(), is(3));
    }

    @Test
    public void whenPushNullAndPop() {
        linked.push(null);
        assertNull(linked.pop());
    }

    @Test (expected = NoSuchElementException.class)
    public void whenPopFromEmptyThenNSEE() {
        assertNull(linked.pop());
    }

}