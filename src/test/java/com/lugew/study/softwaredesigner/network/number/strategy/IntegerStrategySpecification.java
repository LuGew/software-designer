package com.lugew.study.softwaredesigner.network.number.strategy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author LuGew
 * @since 2020/5/12
 */
public class IntegerStrategySpecification {
    Strategy strategy;

    @BeforeEach
    void setUp() {
        strategy = StrategyFactory.getStrategy("Integer");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenTrueFormWhenInput1ThenOutput00000000000000000000000000000001() {
        assertThat(strategy.trueForm(1L))
                .isEqualTo("00000000000000000000000000000001".toCharArray());
    }

    @Test
    void givenTrueFormWhenInputNegative1ThenOutput10000000000000000000000000000001() {
        assertThat(strategy.trueForm(-1L))
                .isEqualTo("10000000000000000000000000000001".toCharArray());
    }

    @Test
    void givenTrueFormWhenInputIntegerMinValueThenOutput11111111111111111111111111111111() {
        assertThat(strategy.trueForm(Integer.MIN_VALUE + 1L))
                .isEqualTo("11111111111111111111111111111111".toCharArray());
    }

    @Test
    void givenTrueFormWhenInputIntegerMaxValueThenOutput01111111111111111111111111111111() {
        assertThat(strategy.trueForm((long) Integer.MAX_VALUE))
                .isEqualTo("01111111111111111111111111111111".toCharArray());
    }

    @Test
    void givenTrueFormWhenInput6ThenOutput00000000000000000000000000000110() {
        assertThat(strategy.trueForm(6L))
                .isEqualTo("00000000000000000000000000000110".toCharArray());
    }

    @Test
    void givenTrueFormWhenInputNegative6ThenOutput10000000000000000000000000000110() {
        assertThat(strategy.trueForm(-6L))
                .isEqualTo("10000000000000000000000000000110".toCharArray());
    }

    @Test
    void givenTrueFormWhenInput0ThenOutput00000000000000000000000000000000() {
        assertThat(strategy.trueForm(0L))
                .isEqualTo("00000000000000000000000000000000".toCharArray());
    }

    /* @Test
     void givenTrueFormWhenInputNegative0ThenOutput00000000000000000000000000000000() {
         assertThat(strategy.trueForm(-0))
                 .isEqualTo("10000000000000000000000000000000".toCharArray());
     }*/

    /*one's complement*/
    @Test
    void givenOnesComplementWhenInput0ThenOutput00000000000000000000000000000000() {
        assertThat(strategy.onesComplement(0L))
                .isEqualTo("00000000000000000000000000000000".toCharArray());
    }

    @Test
    void givenOnesComplementWhenInputMaxValueThenOutput01111111111111111111111111111111() {
        assertThat(strategy.onesComplement((long) Integer.MAX_VALUE))
                .isEqualTo("01111111111111111111111111111111".toCharArray());
    }

    @Test
    void givenOnesComplementWhenInputMinValueThenOutput11111111111111111111111111111111() {
        assertThat(strategy.onesComplement(Integer.MIN_VALUE + 1L))
                .isEqualTo("10000000000000000000000000000000".toCharArray());
    }

    @Test
    void givenOnesComplementWhenInputNegative7ThenOutput10000000000000000000000000000111() {
        assertThat(strategy.onesComplement(-7L))
                .isEqualTo("11111111111111111111111111111000".toCharArray());
    }

    @Test
    void givenOnesComplementWhenInput7ThenOutput00000000000000000000000000000111() {
        assertThat(strategy.onesComplement(7L))
                .isEqualTo("00000000000000000000000000000111".toCharArray());
    }

    /*two's complement*/
    @Test
    void givenTwosComplementWhenInput7ThenOutput00000000000000000000000000000111() {
        assertThat(strategy.twosComplement(7L))
                .isEqualTo("00000000000000000000000000000111".toCharArray());
    }

    @Test
    void givenTwosComplementWhenInputNegative7ThenOutput10000000000000000000000000000111() {
        assertThat(strategy.twosComplement(-7L))
                .isEqualTo("11111111111111111111111111111001".toCharArray());
    }

    @Test
    void givenTwosComplementWhenInputMaxValueThenOutput01111111111111111111111111111111() {
        assertThat(strategy.twosComplement((long) Integer.MAX_VALUE))
                .isEqualTo("01111111111111111111111111111111".toCharArray());
    }

    @Test
    void givenTwosComplementWhenInputMinValueThenOutput10000000000000000000000000000000() {
        assertThat(strategy.twosComplement((long) Integer.MIN_VALUE))
                .isEqualTo("10000000000000000000000000000000".toCharArray());
    }

    @Test
    void givenTwosComplementWhenInputMinValuePlus1ThenOutput10000000000000000000000000000001() {
        assertThat(strategy.twosComplement(Integer.MIN_VALUE + 1L))
                .isEqualTo("10000000000000000000000000000001".toCharArray());
    }

    @Test
    void givenOffsetBinaryWhenInputMinValuePlus1ThenOutput00000000000000000000000000000001() {
        assertThat(strategy.offsetBinary(Integer.MIN_VALUE + 1L))
                .isEqualTo("00000000000000000000000000000001".toCharArray());
    }

    @Test
    void givenOffsetBinaryWhenInputMaxValueThenOutput11111111111111111111111111111111() {
        assertThat(strategy.offsetBinary((long) Integer.MAX_VALUE))
                .isEqualTo("11111111111111111111111111111111".toCharArray());
    }

    @Test
    void givenOffsetBinaryWhenInput0ThenOutput10000000000000000000000000000000() {
        assertThat(strategy.offsetBinary(0L))
                .isEqualTo("10000000000000000000000000000000".toCharArray());
    }

    @Test
    void givenOffsetBinaryWhenInputNegative1ThenOutput01111111111111111111111111111111() {
        assertThat(strategy.offsetBinary(-1L))
                .isEqualTo("01111111111111111111111111111111".toCharArray());
    }

    @Test
    void givenBinaryInputAnyThenOutputSameAsTwddddddosComplement() {
        assertThat(strategy.binary(1L)).isEqualTo(strategy.twosComplement(1L));
        assertThat(strategy.binary(0L)).isEqualTo(strategy.twosComplement(0L));
        assertThat(strategy.binary(-1L)).isEqualTo(strategy.twosComplement(-1L));
        assertThat(strategy.binary((long) Integer.MAX_VALUE)).isEqualTo(strategy.twosComplement((long) Integer.MAX_VALUE));
        assertThat(strategy.binary((long) (Integer.MIN_VALUE + 1))).isEqualTo(strategy.twosComplement((long) (Integer.MIN_VALUE + 1)));
    }
}