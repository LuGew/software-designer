package com.lugew.study.softwaredesigner.computerorganization.checkcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
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

    @Test
    void when0000() {
    }
}