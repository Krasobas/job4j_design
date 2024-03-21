package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
class NameLoadTest {

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkNamesArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void checkDoesNotContainSeparator() {
        NameLoad nameLoad = new NameLoad();
        String name = "key:value";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                /*проверяем, что в сообщении есть соответствующие параметры:*/
                .hasMessageContaining(name)
                /*проверяем наличие конкретного слова в сообщении:*/
                .hasMessageContaining("does not contain the symbol \"=\"");
    }

    @Test
    void checkDoesNotContainKey() {
        NameLoad nameLoad = new NameLoad();
        String name = "=value";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                /*проверяем, что в сообщении есть соответствующие параметры:*/
                .hasMessageContaining(name)
                /*проверяем наличие конкретного слова в сообщении:*/
                .hasMessageContaining("does not contain a key");
    }

    @Test
    void checkDoesNotContainValue() {
        NameLoad nameLoad = new NameLoad();
        String name = "key=";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                /*проверяем, что в сообщении есть соответствующие параметры:*/
                .hasMessageContaining(name)
                /*проверяем наличие конкретного слова в сообщении:*/
                .hasMessageContaining("does not contain a value");
    }

}