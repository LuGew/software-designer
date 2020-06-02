package com.lugew.study.softwaredesigner.computerorganization.checkcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author LuGew
 * @since 2020/6/2
 */
class ParityCheckCodeSpecification {
    private ParityCheckCode checkCode;

    @BeforeEach
    void setUp() {
        checkCode = new ParityCheckCode();
    }

    private void setLittleEndian() {
        checkCode.setEndian(ParityCheckCode.Endian.LITTLE);

    }

    private void setOdd() {
        checkCode.setMethod(ParityCheckCode.Method.ODD);

    }

    @Test
    void givenBigEndianAndEvenWhen0000Then00000() {
        assertThat(checkCode.generate("0000".toCharArray()))
                .isEqualTo("00000".toCharArray());
    }

    @Test
    void givenBigEndianAndEvenWhen1111Then01111() {
        assertThat(checkCode.generate("1111".toCharArray()))
                .isEqualTo("01111".toCharArray());
    }

    @Test
    void givenBigEndianAndEvenWhen0111Then10111() {
        assertThat(checkCode.generate("0111".toCharArray()))
                .isEqualTo("10111".toCharArray());
    }

    @Test
    void whenNullThenException() {
        assertThatThrownBy(() -> {
            checkCode.generate(null);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("input is null");
    }

    @Test
    void whenNotBinaryThenException() {
        assertThatThrownBy(() -> {
            checkCode.generate("2".toCharArray());
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("input not binary");
    }

    @Test
    void givenLittleEndianAndEvenWhen0000Then00000() {
        setLittleEndian();
        assertThat(checkCode.generate("0000".toCharArray()))
                .isEqualTo("00000".toCharArray());
    }

    @Test
    void givenLittleEndianAndEvenWhen1111Then11110() {
        setLittleEndian();
        assertThat(checkCode.generate("1111".toCharArray()))
                .isEqualTo("11110".toCharArray());
    }

    @Test
    void givenLittleEndianAndEvenWhen0111Then01111() {
        setLittleEndian();

        assertThat(checkCode.generate("0111".toCharArray()))
                .isEqualTo("01111".toCharArray());
    }

    @Test
    void givenLittleEndianAndOddWhen0111Then01110() {
        setLittleEndian();
        setOdd();
        assertThat(checkCode.generate("0111".toCharArray()))
                .isEqualTo("01110".toCharArray());
    }

    @Test
    void givenLittleEndianAndOddWhen0000Then00001() {
        setLittleEndian();
        setOdd();
        assertThat(checkCode.generate("0000".toCharArray()))
                .isEqualTo("00001".toCharArray());
    }

    @Test
    void givenLittleEndianAndOddWhen1111Then11111() {
        setLittleEndian();
        setOdd();
        assertThat(checkCode.generate("1111".toCharArray()))
                .isEqualTo("11111".toCharArray());
    }

    @Test
    void givenBigEndianAndOddWhen1111Then11111() {
        setOdd();
        assertThat(checkCode.generate("1111".toCharArray()))
                .isEqualTo("11111".toCharArray());
    }

    @Test
    void givenBigEndianAndOddWhen0000Then10000() {
        setOdd();
        assertThat(checkCode.generate("0000".toCharArray()))
                .isEqualTo("10000".toCharArray());
    }

    @Test
    void givenEvenWhen0000ThenTrue() {
        assertThat(checkCode.check("0000".toCharArray())).isTrue();
    }

    @Test
    void givenEvenWhen0001ThenFalse() {
        assertThat(checkCode.check("0001".toCharArray())).isFalse();
    }

    @Test
    void givenEvenWhen1111ThenTrue() {
        assertThat(checkCode.check("1111".toCharArray())).isTrue();
    }

    @Test
    void givenOddWhen1111ThenFalse() {
        setOdd();
        assertThat(checkCode.check("1111".toCharArray())).isFalse();
    }

    @Test
    void givenOddWhen0000ThenFalse() {
        setOdd();
        assertThat(checkCode.check("0000".toCharArray())).isFalse();
    }

    @Test
    void givenOddWhen0001ThenTrue() {
        setOdd();
        assertThat(checkCode.check("0001".toCharArray())).isTrue();
    }


}