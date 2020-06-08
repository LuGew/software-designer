package com.lugew.study.softwaredesigner.computerorganization.checkcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        checkCode.setInformationBits(7);
        assertThat(checkCode.correct("101110010101".toCharArray()))
                .isEqualTo("001110010101".toCharArray());
    }

    @Test
    void whenE0ErrorAndOtherErrorOf101010010101Then001110010101() {
        checkCode.setInformationBits(7);
        assertThatThrownBy(() -> {
            checkCode.correct("101010010101".toCharArray());
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("2 bits error");
    }

    @Test
    void whenE0NotErrorAndOtherHas2ErrorOf001110010101ThenException() {
        checkCode.setInformationBits(7);
        assertThatThrownBy(() -> {
            checkCode.correct("000010010101".toCharArray());
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("2 bits error");
    }

    @Test
    void whenE0NotErrorAndOtherNotErrorOf001110010101Then001110010101() {
        checkCode.setInformationBits(7);
        assertThat(checkCode.correct("001110010101".toCharArray()))
                .isEqualTo("001110010101".toCharArray());
    }

    @Test
    void whenE0ErrorAndOtherNotErrorOf101110010101Then001110010101() {
        checkCode.setInformationBits(7);
        assertThat(checkCode.correct("101110010101".toCharArray()))
                .isEqualTo("001110010101".toCharArray());
    }

    @Test
    void whenE0NotErrorAndOtherHasErrorOf001010010101Then001110010101() {
        checkCode.setInformationBits(7);
        assertThat(checkCode.correct("001010010101".toCharArray()))
                .isEqualTo("001110010101".toCharArray());
    }

    @Test
    void whenEvenThenEven() {
        checkCode.enableOddExtendMethod();
        assertThat(checkCode.getExtendCheckMethod())
                .isEqualTo(ParityCheckCode.CheckMethod.ODD);
    }

    @Test
    void whenHas7InformationThen7Information() {
        checkCode = new ExtendHammingCheckCode(7);
        assertThat(checkCode.getInformationBits())
                .isEqualTo(7);
    }
}