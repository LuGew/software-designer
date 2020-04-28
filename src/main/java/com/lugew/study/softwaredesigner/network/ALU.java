package com.lugew.study.softwaredesigner.network;

/**
 * 算术逻辑单元 Arithmetic and Logic Unit
 *
 * @author LuGew
 * @since 2020/4/28
 */
public interface ALU {

    void plus(Number n0, Number n1);

    void minus(Number minuend, Number reduction);

    void multiply(Number n0, Number n1);

    void divide(Number dividend, Number divisor);

    void and(Number n0, Number n1);

    void or(Number n0, Number n1);

    void not(Number n0, Number n1);
}
