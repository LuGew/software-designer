package com.lugew.study.softwaredesigner.computerorganization.checkcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * n有效位，k校验位
 * 需满足2^k-1>=n+k
 * 有
 * k    |   n
 * 2    |   1
 * 3    |   2~4
 * 4    |   5~11
 * 5    |   12~26
 * 6    |   27~57
 * 7    |   58~120
 * 能够检一位错，纠一位错
 *
 * @author LuGew
 * @since 2020/6/2
 */
class HammingCheckCodeSpecification {
    private HammingCheckCode checkCode;

    @BeforeEach
    void setUp() {
        checkCode = new HammingCheckCode();
    }

    void setInformationBits(int informationBits) {
        checkCode.initialize(informationBits);
    }

    /*generate*/
    @Test
    void givenK2N1EvenWhen0Then000() {
        assertThat(checkCode.generate("0".toCharArray()))
                .isEqualTo("000".toCharArray());
    }

    @Test
    void givenK2N1EvenWhen1Then111() {
        assertThat(checkCode.generate("1".toCharArray()))
                .isEqualTo("111".toCharArray());
    }

    @Test
    void givenK2N1OddWhen1Then001() {
        checkCode.enableOddMode();
        assertThat(checkCode.generate("1".toCharArray()))
                .isEqualTo("001".toCharArray());
    }

    @Test
    void givenK2N1OddWhen0Then110() {
        checkCode.enableOddMode();
        assertThat(checkCode.generate("0".toCharArray()))
                .isEqualTo("110".toCharArray());
    }

    @Test
    void givenK3N2OddWhen00Then11010() {
        checkCode.enableOddMode();
        assertThat(checkCode.generate("00".toCharArray()))
                .isEqualTo("11010".toCharArray());
    }

    @Test
    void givenK3N2OddWhen01Then01001() {
        checkCode.enableOddMode();
        assertThat(checkCode.generate("01".toCharArray()))
                .isEqualTo("01001".toCharArray());
    }

    @Test
    void givenK3N2OddWhen10Then00110() {
        checkCode.enableOddMode();
        assertThat(checkCode.generate("10".toCharArray()))
                .isEqualTo("00110".toCharArray());
    }

    @Test
    void givenK3N2OddWhen11Then10101() {
        checkCode.enableOddMode();
        assertThat(checkCode.generate("11".toCharArray()))
                .isEqualTo("10101".toCharArray());
    }

    @Test
    void givenK4N7OddWhen0000000Then11010001000() {
        checkCode.enableOddMode();
        assertThat(checkCode.generate("0000000".toCharArray()))
                .isEqualTo("11010001000".toCharArray());
    }

    @Test
    void givenK4N7EvenWhen0000000Then00000000000() {
        assertThat(checkCode.generate("0000000".toCharArray()))
                .isEqualTo("00000000000".toCharArray());
    }

    @Test
    void givenK4N7EvenWhen1111111Then11111111111() {
        assertThat(checkCode.generate("1111111".toCharArray()))
                .isEqualTo("11111111111".toCharArray());
    }

    @Test
    void givenK4N7EvenWhen1001101Then01110010101() {
        assertThat(checkCode.generate("1001101".toCharArray()))
                .isEqualTo("01110010101".toCharArray());
    }

    @Test
    void givenK4N7EvenWhen1001101Then10100011101() {
        checkCode.enableOddMode();
        assertThat(checkCode.generate("1001101".toCharArray()))
                .isEqualTo("10100011101".toCharArray());
    }

    @Test
    void when2ThenException() {
        assertThatThrownBy(() -> {
            checkCode.generate("2".toCharArray());
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("input not binary");
    }

    @Test
    void whenNullThenException() {
        assertThatThrownBy(() -> {
            checkCode.generate(null);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("input is null");
    }

    /*check*/
    @Test
    void when000ThenTrue() {
        checkCode.setMode(3);
        assertThat(checkCode.check("000".toCharArray()))
                .isTrue();
    }
}