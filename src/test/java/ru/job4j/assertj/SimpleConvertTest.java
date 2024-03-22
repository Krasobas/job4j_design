package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).isNotEmpty()
                .hasSize(5)
                .startsWith("first")
                .endsWith("five")
                .containsAll(List.of("second", "three", "four"))
                .doesNotContain("six");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("java is very very very very powerful and cool".split(" "));
        assertThat(set).isNotEmpty()
                .hasSize(6)
                .contains("java")
                .doesNotContain("script")
                .allSatisfy(s -> assertThat(s.length() < 9))
                .anyMatch(s -> s.length() < 3)
                .containsOnlyOnce("very");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("hi map i'll check you here".split(" "));
        assertThat(map).isNotEmpty()
                .hasSize(6)
                .containsKeys("hi", "you", "i'll")
                .containsValues(2, 3, 5)
                .doesNotContainValue(6)
                .containsEntry("i'll", 2);
    }
}