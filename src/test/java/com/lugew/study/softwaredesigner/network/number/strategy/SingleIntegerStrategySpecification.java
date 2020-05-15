package com.lugew.study.softwaredesigner.network.number.strategy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LuGew
 * @since 2020/5/12
 */
class SingleIntegerStrategySpecification {
    SingleIntegerStrategy singleIntegerStrategy;

    @BeforeEach
    void setUp() {
        singleIntegerStrategy = SingleIntegerStrategy.getInstance();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenTrueFormWhenInput1ThenOutput00000000000000000000000000000001() {
        assertThat(singleIntegerStrategy.trueForm(1))
                .isEqualTo("00000000000000000000000000000001".toCharArray());
    }

    @Test
    void givenTrueFormWhenInputNegative1ThenOutput10000000000000000000000000000001() {
        assertThat(singleIntegerStrategy.trueForm(-1))
                .isEqualTo("10000000000000000000000000000001".toCharArray());
    }

    @Test
    void givenTrueFormWhenInputIntegerMinValueThenOutput10000000000000000000000000000000() {
        assertThat(singleIntegerStrategy.trueForm(Integer.MIN_VALUE))
                .isEqualTo("10000000000000000000000000000000".toCharArray());
    }

    @Test
    void givenTrueFormWhenInputIntegerMaxValueThenOutput01111111111111111111111111111111() {
        assertThat(singleIntegerStrategy.trueForm(Integer.MAX_VALUE))
                .isEqualTo("01111111111111111111111111111111".toCharArray());
    }

    @Test
    void givenTrueFormWhenInput6ThenOutput00000000000000000000000000000110() {
        assertThat(singleIntegerStrategy.trueForm(6))
                .isEqualTo("00000000000000000000000000000110".toCharArray());
    }

    @Test
    void givenTrueFormWhenInputNegative6ThenOutput10000000000000000000000000000110() {
        assertThat(singleIntegerStrategy.trueForm(-6))
                .isEqualTo("10000000000000000000000000000110".toCharArray());
    }

    @Test
    void givenTrueFormWhenInput0ThenOutput00000000000000000000000000000000() {
        assertThat(singleIntegerStrategy.trueForm(0))
                .isEqualTo("00000000000000000000000000000000".toCharArray());
    }
}