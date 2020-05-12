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
}