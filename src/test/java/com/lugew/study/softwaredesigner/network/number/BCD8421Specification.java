package com.lugew.study.softwaredesigner.network.number;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BCD8421Specification {
    private BCD bcd;

    @BeforeEach
    void setUp() {
        bcd = new BCD8421();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void when0000Then0() {
        assertThat(bcd.toDecimal("0000".toCharArray())).isEqualTo(0);
    }

    @Test
    void when0001Then1() {
        assertThat(bcd.toDecimal("0001".toCharArray())).isEqualTo(1);
    }

    @Test
    void when0011Then3() {
        assertThat(bcd.toDecimal("0011".toCharArray())).isEqualTo(3);
    }

    @Test
    void when0010Then2() {
        assertThat(bcd.toDecimal("0010".toCharArray())).isEqualTo(2);
    }

    @Test
    void when0100Then4() {
        assertThat(bcd.toDecimal("0100".toCharArray())).isEqualTo(4);
    }

    @Test
    void when0101Then5() {
        assertThat(bcd.toDecimal("0101".toCharArray())).isEqualTo(5);
    }

    @Test
    void when0110Then6() {
        assertThat(bcd.toDecimal("0110".toCharArray())).isEqualTo(6);
    }

    @Test
    void when0111Then7() {
        assertThat(bcd.toDecimal("0111".toCharArray())).isEqualTo(7);
    }

    @Test
    void when1000Then8() {
        assertThat(bcd.toDecimal("1000".toCharArray())).isEqualTo(8);
    }

    @Test
    void when1001Then9() {
        assertThat(bcd.toDecimal("1001".toCharArray())).isEqualTo(9);
    }

    @Test
    void when10010ThenException() {

        assertThatThrownBy(() -> {
            bcd.toDecimal("10010".toCharArray());
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("length can't more than 4");
    }

    @Test
    void when100ThenException() {

        assertThatThrownBy(() -> {
            bcd.toDecimal("100".toCharArray());
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("length can't less than 4");
    }

    @Test
    void whenNullThenException() {
        assertThatThrownBy(() -> {
            bcd.toDecimal(null);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("content can't be null");
    }
}