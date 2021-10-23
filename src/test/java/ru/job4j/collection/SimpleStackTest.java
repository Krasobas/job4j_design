package ru.job4j.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleStackTest {

    @Test
    public void whenMultiPushAndPop() {
        SimpleStack<Integer> linked = new SimpleStack<>();
        linked.push(1);
        linked.push(2);
        linked.push(3);
        assertThat(linked.pop(), is(3));
    }

    @Test
    public void whenPushNullAndPop() {
        SimpleStack<Integer> linked = new SimpleStack<>();
        linked.push(null);
        assertNull(linked.pop());
    }

    @Test (expected = NoSuchElementException.class)
    public void whenPopFromEmptyThenNSEE() {
        SimpleStack<Integer> linked = new SimpleStack<>();
        assertNull(linked.pop());
    }

}