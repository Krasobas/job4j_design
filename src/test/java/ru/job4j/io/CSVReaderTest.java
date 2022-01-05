package ru.job4j.io;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;

public class CSVReaderTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test (expected = IllegalArgumentException.class)
    public void whenWrongSourceFileType() throws Exception {
        File file = temporaryFolder.newFile("source.pdf");
        File target = temporaryFolder.newFile("target.csv");
        String[] args = new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name,age"
        };
        new CSVReader(args);
    }

    @Test
    public void whenDifferentTargetFileType() throws Exception {
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.txt");
        String[] args = new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name,age"
        };
        Files.writeString(file.toPath(), " ");
        CSVReader reader = new CSVReader(args);
        String expected = "";
        reader.handle();
        Assert.assertEquals(expected, Files.readString(target.toPath()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenThreeArgumentsWereEntered() throws Exception {
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.txt");
        String[] args = new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath()
        };
        CSVReader reader = new CSVReader(args);
        reader.handle();
    }

    @Test
    public void whenNonexistentFilterWasEntered() throws Exception {
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.txt");
        String[] args = new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=surname,age"
        };
        Files.writeString(file.toPath(), " ");
        CSVReader reader = new CSVReader(args);
        String expected = "";
        reader.handle();
        Assert.assertEquals(expected, Files.readString(target.toPath()));
    }

    @Test
    public void whenStdoutAndOneFilter() throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = temporaryFolder.newFile("source.csv");
        Files.writeString(file.toPath(), data);
        String[] args = new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=stdout", "-filter=age"
        };
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        CSVReader reader = new CSVReader(args);
        String expected = String.join(
                System.lineSeparator(),
                "age",
                "20",
                "25",
                "30"
        ).concat(System.lineSeparator());
        reader.handle();
        Assert.assertEquals(expected, out.toString());
    }

    @Test
    public void whenFilterTwoColumns() throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        String[] args = new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name,age"
        };
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "name;age",
                "Tom;20",
                "Jack;25",
                "William;30"
        ).concat(System.lineSeparator());
        CSVReader reader = new CSVReader(args);
        reader.handle();
        Assert.assertEquals(expected, Files.readString(target.toPath()));
    }

    @Test
    public void whenSomeFiltersInRandomOrder() throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        String[] args = new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=last_name,education,name"
        };
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "name;last_name;education",
                "Tom;Smith;Bachelor",
                "Jack;Johnson;Undergraduate",
                "William;Brown;Secondary special"
        ).concat(System.lineSeparator());
        CSVReader reader = new CSVReader(args);
        reader.handle();
        Assert.assertEquals(expected, Files.readString(target.toPath()));
    }

}