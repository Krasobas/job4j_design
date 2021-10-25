package ru.job4j.it;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.addBefore(input, 1, 4);
        assertThat(input, is(List.of(1, 4, 2, 3)));
    }

    @Test
    public void whenAddBeforeAndIndexZero() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.addBefore(input, 0, 4);
        assertThat(input, is(List.of(4, 1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.addBefore(input, 3, 4);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.addAfter(input, 2, 4);
        assertThat(input, is(List.of(1, 2, 3, 4)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.addAfter(input, 3, 4);
    }

    @Test
    public void whenThenRemoveIfAndPredicateFalse() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.removeIf(input, i -> i < 0);
        assertThat(input, is(List.of(1, 2, 3)));
    }

    @Test
    public void whenThenRemoveIfAndPredicateTrue() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.removeIf(input, i -> i < 3);
        assertThat(input, is(List.of(3)));
    }

    @Test
    public void whenThenReplaceIfAndPredicateFalse() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.replaceIf(input, i -> i < 0, 0);
        assertThat(input, is(List.of(1, 2, 3)));
    }

    @Test
    public void whenThenReplaceIfAndPredicateTrue() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.replaceIf(input, i -> i < 3, 0);
        assertThat(input, is(List.of(0, 0, 3)));
    }

    @Test
    public void whenRemoveAllAndMatch() {
        List<Integer> input = new ArrayList<>(List.of(1, 3, 4, 1, 5, 0, 4));
        ListUtils.removeAll(input, List.of(0, 1, 4));
        assertThat(input, is(List.of(3, 5)));
    }

    @Test
    public void whenRemoveAllAndDoNotMatch() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.removeAll(input, List.of(4, 5));
        assertThat(input, is(List.of(1, 2, 3)));
    }

}