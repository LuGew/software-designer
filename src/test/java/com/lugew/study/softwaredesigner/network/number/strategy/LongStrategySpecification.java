package com.lugew.study.softwaredesigner.network.number.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author LuGew
 * @since 2020/5/17
 */
public class LongStrategySpecification {
    Strategy<Long> strategy;

    @BeforeEach
    void setUp() {
        strategy = StrategyFactory.getStrategy("Long");
    }

    @Test
    void givenTrueFormWhenInput1ThenOutput0000000000000000000000000000000000000000000000000000000000000001() {
        assertThat(strategy.trueForm(1L))
                .isEqualTo("0000000000000000000000000000000000000000000000000000000000000001".toCharArray());
    }

    @Test
    void givenTrueFormWhenInputNegative1ThenOutput1000000000000000000000000000000000000000000000000000000000000001() {
        assertThat(strategy.trueForm(-1L))
                .isEqualTo("1000000000000000000000000000000000000000000000000000000000000001".toCharArray());
    }

    @Test
    void givenTrueFormWhenInputIntegerMinValueThenOutput1111111111111111111111111111111111111111111111111111111111111111() {
        assertThat(strategy.trueForm(Long.MIN_VALUE + 1L))
                .isEqualTo("1111111111111111111111111111111111111111111111111111111111111111".toCharArray());
    }

    @Test
    void givenTrueFormWhenInputIntegerMaxValueThenOutput0111111111111111111111111111111111111111111111111111111111111111() {
        assertThat(strategy.trueForm(Long.MAX_VALUE))
                .isEqualTo("0111111111111111111111111111111111111111111111111111111111111111".toCharArray());
    }

    @Test
    void givenTrueFormWhenInput6ThenOutput0000000000000000000000000000000000000000000000000000000000000110() {
        assertThat(strategy.trueForm(6L))
                .isEqualTo("0000000000000000000000000000000000000000000000000000000000000110".toCharArray());
    }

    @Test
    void givenTrueFormWhenInputNegative6ThenOutput1000000000000000000000000000000000000000000000000000000000000110() {
        assertThat(strategy.trueForm(-6L))
                .isEqualTo("1000000000000000000000000000000000000000000000000000000000000110".toCharArray());
    }

    @Test
    void givenTrueFormWhenInput0ThenOutput0000000000000000000000000000000000000000000000000000000000000000() {
        assertThat(strategy.trueForm(0L))
                .isEqualTo("0000000000000000000000000000000000000000000000000000000000000000".toCharArray());
    }

    /*one's complement*/
    @Test
    void givenOnesComplementWhenInput0ThenOutput0000000000000000000000000000000000000000000000000000000000000000() {
        assertThat(strategy.onesComplement(0L))
                .isEqualTo("0000000000000000000000000000000000000000000000000000000000000000".toCharArray());
    }

    @Test
    void givenOnesComplementWhenInputMaxValueThenOutput0111111111111111111111111111111111111111111111111111111111111111() {
        assertThat(strategy.onesComplement(Long.MAX_VALUE))
                .isEqualTo("0111111111111111111111111111111111111111111111111111111111111111".toCharArray());
    }

    @Test
    void givenOnesComplementWhenInputMinValueThenOutput1000000000000000000000000000000000000000000000000000000000000000() {
        assertThat(strategy.onesComplement(Long.MIN_VALUE + 1L))
                .isEqualTo("1000000000000000000000000000000000000000000000000000000000000000".toCharArray());
    }

    @Test
    void givenOnesComplementWhenInputNegative7ThenOutput1111111111111111111111111111111111111111111111111111111111111000() {
        assertThat(strategy.onesComplement(-7L))
                .isEqualTo("1111111111111111111111111111111111111111111111111111111111111000".toCharArray());
    }

    @Test
    void givenOnesComplementWhenInput7ThenOutput0000000000000000000000000000000000000000000000000000000000000111() {
        assertThat(strategy.onesComplement(7L))
                .isEqualTo("0000000000000000000000000000000000000000000000000000000000000111".toCharArray());
    }

    /*two's complement*/
    @Test
    void givenTwosComplementWhenInput7ThenOutput0000000000000000000000000000000000000000000000000000000000000111() {
        assertThat(strategy.twosComplement(7L))
                .isEqualTo("0000000000000000000000000000000000000000000000000000000000000111".toCharArray());
    }

    @Test
    void givenTwosComplementWhenInputNegative7ThenOutput1111111111111111111111111111111111111111111111111111111111111001() {
        assertThat(strategy.twosComplement(-7L))
                .isEqualTo("1111111111111111111111111111111111111111111111111111111111111001".toCharArray());
    }

    @Test
    void givenTwosComplementWhenInputMaxValueThenOutput0111111111111111111111111111111111111111111111111111111111111111() {
        assertThat(strategy.twosComplement(Long.MAX_VALUE))
                .isEqualTo("0111111111111111111111111111111111111111111111111111111111111111".toCharArray());
    }

    @Test
    void givenTwosComplementWhenInputMinValueThenOutput1000000000000000000000000000000000000000000000000000000000000000() {
        assertThat(strategy.twosComplement(Long.MIN_VALUE))
                .isEqualTo("1000000000000000000000000000000000000000000000000000000000000000".toCharArray());
    }

    @Test
    void givenTwosComplementWhenInputMinValuePlus1ThenOutput1000000000000000000000000000000000000000000000000000000000000001() {
        assertThat(strategy.twosComplement(Long.MIN_VALUE + 1L))
                .isEqualTo("1000000000000000000000000000000000000000000000000000000000000001".toCharArray());
    }

    @Test
    void givenOffsetBinaryWhenInputMinValuePlus1ThenOutput0000000000000000000000000000000000000000000000000000000000000001() {
        assertThat(strategy.offsetBinary(Long.MIN_VALUE + 1L))
                .isEqualTo("0000000000000000000000000000000000000000000000000000000000000001".toCharArray());
    }

    @Test
    void givenOffsetBinaryWhenInputMaxValueThenOutput1111111111111111111111111111111111111111111111111111111111111111() {
        assertThat(strategy.offsetBinary(Long.MAX_VALUE))
                .isEqualTo("1111111111111111111111111111111111111111111111111111111111111111".toCharArray());
    }

    @Test
    void givenOffsetBinaryWhenInput0ThenOutput1000000000000000000000000000000000000000000000000000000000000000() {
        assertThat(strategy.offsetBinary(0L))
                .isEqualTo("1000000000000000000000000000000000000000000000000000000000000000".toCharArray());
    }

    @Test
    void givenOffsetBinaryWhenInputNegative1ThenOutput0111111111111111111111111111111111111111111111111111111111111111() {
        assertThat(strategy.offsetBinary(-1L))
                .isEqualTo("0111111111111111111111111111111111111111111111111111111111111111".toCharArray());
    }

    @Test
    void givenBinaryInputAnyThenOutputSameAsTwddddddosComplement() {
        assertThat(strategy.binary(1L)).isEqualTo(strategy.twosComplement(1L));
        assertThat(strategy.binary(0L)).isEqualTo(strategy.twosComplement(0L));
        assertThat(strategy.binary(-1L)).isEqualTo(strategy.twosComplement(-1L));
        assertThat(strategy.binary(Long.MAX_VALUE)).isEqualTo(strategy.twosComplement(Long.MAX_VALUE));
        assertThat(strategy.binary(Long.MIN_VALUE + 1)).isEqualTo(strategy.twosComplement((Long.MIN_VALUE + 1)));
    }

}