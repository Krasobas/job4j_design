package ru.job4j.io.duplicates;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class DuplicatesVisitorTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenNoAnyDuplicate() throws IOException {
        File firstParent = folder.newFolder("firstParent");
        File firstChild = folder.newFile("firstParent/firstChild");
        File secondParent = folder.newFolder("secondParent");
        File secondChild = folder.newFile("secondParent/secondChild");
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(folder.getRoot().toPath(), visitor);
        assertTrue(visitor.getDuplicates().isEmpty());
    }

    @Rule
    public TemporaryFolder folder2 = new TemporaryFolder();

    @Test
    public void whenSomeDuplicates() throws IOException {
        File firstParent = folder2.newFolder("firstParent");
        File firstChild = folder2.newFile("firstParent/firstChild");
        File secondChild = folder2.newFile("firstParent/secondChild");
        File secondParent = folder2.newFolder("secondParent");
        File secondChildDuplicate = folder2.newFile("secondParent/secondChild");
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(folder2.getRoot().toPath(), visitor);
        Map<FileProperty, List<Path>> duplicates = visitor.getDuplicates();
        List<Path> expected = List.of(secondChild.toPath(), secondChildDuplicate.toPath());
        assertThat(visitor.getDuplicates().values().size(), is(1));
        assertTrue(duplicates.containsValue(expected));
    }
}