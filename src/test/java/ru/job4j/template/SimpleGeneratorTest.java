package ru.job4j.template;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
@Disabled()
public class SimpleGeneratorTest {

    @Test
    public void whenEmptyTemplateThenException() {
        Generator generator = new SimpleGenerator();
        assertThatThrownBy(() -> generator.produce("", Map.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenTemplateIsNullThenException() {
        Generator generator = new SimpleGenerator();
        assertThatThrownBy(() -> generator.produce(null, Map.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenArgsIsNullThenException() {
        Generator generator = new SimpleGenerator();
        assertThatThrownBy(() -> generator.produce("I am a ${name}, Who are ${subject}? ", null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenOk() {
        Generator generator = new SimpleGenerator();
        String expected = "I am a Petr Arsentev, Who are you? ";
        String got = generator.produce(
                "I am a ${name}, Who are ${subject}? ",
                Map.of("name", "Petr Arsentev", "subject", "you")
                );
        assertEquals(expected, got);
    }

    @Test
    public void whenArgsNotContainsAllKeysThenException() {
        Generator generator = new SimpleGenerator();
        assertThatThrownBy(() -> generator.produce(
                "I am a ${name}, Who are ${subject}? ",
                        Map.of("name", "Petr Arsentev")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenArgsHasMoreKeysAsExpectedThenException() {
        Generator generator = new SimpleGenerator();
        assertThatThrownBy(() -> generator.produce(
                "I am a ${name}, Who are ${subject}? ",
                Map.of("name", "Petr Arsentev", "subject", "you", "another", "error")
        ))
                .isInstanceOf(IllegalArgumentException.class);
    }

}