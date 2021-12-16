package ru.job4j.io.duplicates;

import org.junit.Ignore;
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
}