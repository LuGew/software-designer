package com.lugew.study.softwaredesigner.computerorganization.checkcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author LuGew
 * @since 2020/6/6
 */
class ExtendHammingCheckCodeSpecification {
    private ExtendHammingCheckCode checkCode;

    @BeforeEach
    void setUp() {
        checkCode = new ExtendHammingCheckCode();
    }

    @Test
    void givenBigEndianAndExtendEvenWhen0Then0000() {
        assertThat(checkCode.generate("0".toCharArray()))
                .isEqualTo("0000".toCharArray());
    }

    @Test
    void givenLittleEndianAndExtendEvenWhen0Then0000() {
        assertThat(checkCode.generate("0".toCharArray()))
                .isEqualTo("0000".toCharArray());
    }

    @Test
    void givenLittleEndianAndExtendOddWhen0Then1100() {
        checkCode.enableOddExtendMethod();
        checkCode.enableLittleEndian();
        assertThat(checkCode.generate("0".toCharArray()))
                .isEqualTo("0001".toCharArray());
    }

    @Test
    void givenBigEndianAndExtendEvenWhen1001101Then01110010101() {
        assertThat(checkCode.generate("1001101".toCharArray()))
                .isEqualTo("001110010101".toCharArray());
    }

    @Test
    void when001110010101ThenTrue() {
        assertThat(checkCode.check("001110010101".toCharArray()))
                .isTrue();
    }

    @Test
    void whenE0ErrorOf101110010101Then001110010101() {
        assertThat(checkCode.correct("101110010101".toCharArray()))
                .isEqualTo("001110010101".toCharArray());
    }
}