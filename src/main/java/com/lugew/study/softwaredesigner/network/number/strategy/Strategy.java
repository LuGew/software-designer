package com.lugew.study.softwaredesigner.network.number.strategy;

/**
 * @author LuGew
 * @since 2020/4/28 23:37
 **/
public interface Strategy<T extends Number> {
    /**
     * 二进制
     *
     * @param t 数
     * @return 字符表示
     */
    String binary(T t);

    /**
     * 原码
     *
     * @param t 数
     * @return 字符表示
     */
    String trueForm(T t);

    /**
     * 反码
     *
     * @param t 数
     * @return 字符表示
     */
    String onesComplement(T t);

    /**
     * 补码
     *
     * @param t 数
     * @return 字符表示
     */
    String twosComplement(T t);

    /**
     * 移码
     *
     * @param t 数
     * @return 字符表示
     */
    String offsetBinary(T t);
}
