package ru.job4j.kiss.fool;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

class FoolTest {


    @Test
    void checkRunOk() {
        String expected = String.join(System.lineSeparator(), List.of("Игра FizzBuzz.", "1", "Fizz", "Buzz", "7", "Fizz", "11", "13", "FizzBuzz", ""));
        String userAnswers = String.join(System.lineSeparator(), List.of("2", "4", "Fizz", "8", "Buzz", "Fizz", "14", "16"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);
        new Fool(new ByteArrayInputStream(userAnswers.getBytes()), out).run(16);
        assertThat(baos.toString()).hasToString(expected);
    }

    @Test
    void checkRunWithError() {
        String expected = String.join(System.lineSeparator(), List.of("Игра FizzBuzz.", "1", "Ошибка. Начинай снова.", "1", "Fizz", "Buzz", "7", "Fizz", "11", "13", "FizzBuzz", ""));
        String userAnswers = String.join(System.lineSeparator(), List.of("3", "2", "4", "Fizz", "8", "Buzz", "Fizz", "14", "16"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);
        new Fool(new ByteArrayInputStream(userAnswers.getBytes()), out).run(16);
        assertThat(baos.toString()).hasToString(expected);
    }

    @Test
    void checkRunWhenThrow() {
        String expected = String.join(System.lineSeparator(), List.of("Игра FizzBuzz.", "1", "Fizz", "Произошла ошибка, запустите игру заново.", ""));
        String userAnswers = String.join(System.lineSeparator(), List.of("2"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(baos);
        new Fool(new ByteArrayInputStream(userAnswers.getBytes()), out).run(16);
        assertThat(baos.toString()).hasToString(expected);
    }

}