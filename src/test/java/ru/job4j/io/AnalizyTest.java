package ru.job4j.io;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenTwoServerErrorThenTwoLogs() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        StringBuilder result = new StringBuilder();
        result.append("10:57:01;10:59:01;");
        result.append("11:01:02;11:02:02;");
        try (PrintWriter out = new PrintWriter(source.getAbsolutePath())) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        new Analizy().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder log = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target.getAbsolutePath()))) {
            in.lines().forEach(log::append);
            assertThat(log.toString(), is(result.toString()));
        }
    }

    @Test
    public void whenOneServerErrorThenOneLog() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        StringBuilder result = new StringBuilder();
        result.append("10:57:01;11:02:02;");
        try (PrintWriter out = new PrintWriter(source.getAbsolutePath())) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("200 11:02:02");
        }
        new Analizy().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder log = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target.getAbsolutePath()))) {
            in.lines().forEach(log::append);
            assertThat(log.toString(), is(result.toString()));
        }
    }

    @Test
    public void whenNoServerErrorThenEmptyLog() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source.getAbsolutePath())) {
            out.println("200 10:56:01");
            out.println("300 10:57:01");
            out.println("200 10:58:01");
            out.println("300 10:59:01");
            out.println("300 11:01:02");
            out.println("200 11:02:02");
        }
        new Analizy().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder log = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target.getAbsolutePath()))) {
            in.lines().forEach(log::append);
            assertTrue(log.toString().isEmpty());
        }
    }
}