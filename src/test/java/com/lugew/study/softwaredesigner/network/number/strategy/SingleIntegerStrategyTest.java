package com.lugew.study.softwaredesigner.network.number.strategy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SingleIntegerStrategyTest {
    SingleIntegerStrategy singleIntegerStrategy;
    @BeforeEach
    void setUp() {
        singleIntegerStrategy = SingleIntegerStrategy.getInstance();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void binary() {

        assertThat(singleIntegerStrategy)
    }

    @Test
    void trueForm() {
    }

    @Test
    void onesComplement() {
    }

    @Test
    void twosComplement() {
    }

    @Test
    void offsetBinary() {
    }
}