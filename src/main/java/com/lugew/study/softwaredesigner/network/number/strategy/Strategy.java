package com.lugew.study.softwaredesigner.network.number.strategy;

/**
 * @author XINWEN95
 * @since 2020/4/28 23:37
 **/
public interface Strategy<T extends Number> {
    /**
     * 二进制
     *
     * @param t 数
     * @return 字符表示
     */
    char[] binary(T t);

    /**
     * 原码
     *
     * @param t 数
     * @return 字符表示
     */
    char[] trueForm(T t);

    /**
     * 反码
     *
     * @param t 数
     * @return 字符表示
     */
    char[] onesComplement(T t);

    /**
     * 补码
     *
     * @param t 数
     * @return 字符表示
     */
    char[] twosComplement(T t);

    /**
     * 移码
     *
     * @param t 数
     * @return 字符表示
     */
    char[] offsetBinary(T t);

}
