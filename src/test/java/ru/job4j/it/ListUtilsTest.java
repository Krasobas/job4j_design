package ru.job4j.it;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class ListUtilsTest {
    List<Integer> input;

    @Before
    public void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 2, 3));
    }

    @Test
    public void whenAddBefore() {
        ListUtils.addBefore(input, 1, 4);
        assertThat(input, is(List.of(1, 4, 2, 3)));
    }

    @Test
    public void whenAddBeforeAndIndexZero() {
        ListUtils.addBefore(input, 0, 4);
        assertThat(input, is(List.of(4, 1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        ListUtils.addBefore(input, 3, 4);
    }

    @Test
    public void whenAddAfterLast() {
        ListUtils.addAfter(input, 2, 4);
        assertThat(input, is(List.of(1, 2, 3, 4)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterInvalidIndex() {
        ListUtils.addAfter(input, 3, 4);
    }

    @Test
    public void whenThenRemoveIfAndPredicateFalse() {
        ListUtils.removeIf(input, i -> i < 0);
        assertThat(input, is(List.of(1, 2, 3)));
    }

    @Test
    public void whenThenRemoveIfAndPredicateTrue() {
        ListUtils.removeIf(input, i -> i < 3);
        assertThat(input, is(List.of(3)));
    }

    @Test
    public void whenThenReplaceIfAndPredicateFalse() {
        ListUtils.replaceIf(input, i -> i < 0, 0);
        assertThat(input, is(List.of(1, 2, 3)));
    }

    @Test
    public void whenThenReplaceIfAndPredicateTrue() {
        ListUtils.replaceIf(input, i -> i < 3, 0);
        assertThat(input, is(List.of(0, 0, 3)));
    }

    @Test
    public void whenRemoveAllAndMatch() {
        input = new ArrayList<>(List.of(1, 3, 4, 1, 5, 0, 4));
        ListUtils.removeAll(input, List.of(0, 1, 4));
        assertThat(input, is(List.of(3, 5)));
    }

    @Test
    public void whenRemoveAllAndDoNotMatch() {
        ListUtils.removeAll(input, List.of(4, 5));
        assertThat(input, is(List.of(1, 2, 3)));
    }

}