package ru.job4j.assertj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

import org.junit.jupiter.api.Test;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isInstanceOf(String.class)
                .isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isInstanceOf(String.class)
                .isEqualTo("Tetrahedron");
    }

    @Test
    void isNumberOfVertices0() {
        Box box = new Box(0, 10);
        int name = box.getNumberOfVertices();
        assertThat(name).isInstanceOf(Integer.class)
                .isZero();
    }

    @Test
    void isNumberOfVertices4() {
        Box box = new Box(4, 10);
        int name = box.getNumberOfVertices();
        assertThat(name).isInstanceOf(Integer.class)
                .isPositive()
                .isNotZero()
                .isEven()
                .isEqualTo(4);
    }

    @Test
    void isExistTrue() {
        Box box = new Box(0, 10);
        boolean exist = box.isExist();
        assertThat(exist).isInstanceOf(Boolean.class)
                .isTrue();
    }

    @Test
    void isExistFalse() {
        Box box = new Box(11, 10);
        boolean exist = box.isExist();
        assertThat(exist).isInstanceOf(Boolean.class)
                .isFalse();
    }

    @Test
    void isAreaForSphere() {
        Box box = new Box(0, 10);
        double area = box.getArea();
        assertThat(area).isInstanceOf(Double.class)
                .isPositive()
                .isCloseTo(1256.637d, withPrecision(0.1d));
    }

    @Test
    void isAreaForCube() {
        Box box = new Box(8, 10);
        double area = box.getArea();
        assertThat(area).isInstanceOf(Double.class)
                .isPositive()
                .isCloseTo(600d, withPrecision(0.1d));
    }


}