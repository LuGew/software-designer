package com.lugew.study.softwaredesigner.computerorganization.checkcode;

/**
 * 校验码
 *
 * @author LuGew
 * @since 2020/6/2
 */
public interface CheckCode {
    char ONE = '1';
    char ZERO = '0';

    char[] generate(char[] binary);

    boolean check(char[] checkCode);

    char[] correct(char[] binary);
}
