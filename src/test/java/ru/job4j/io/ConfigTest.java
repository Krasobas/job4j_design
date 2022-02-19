package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        assertThat(config.value("name"), is("Vasiliy Krasov"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWitCommentAndEmptyLines() {
        String path = "./data/pair_with_comment_and_empty_lines.properties";
        Config config = new Config(path);
        assertThat(config.value("#Comment"), is(Matchers.nullValue()));
        assertThat(config.value("name"), is("Vasiliy"));
        assertThat(config.value("surname"), is("Krasov"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenNoKeyContractInfraction() {
        String path = "./data/no_key_contract_infraction.properties";
        new Config(path);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenNoSeparatorContractInfraction() {
        String path = "./data/no_separator_contract_infraction.properties";
        new Config(path);
    }
}