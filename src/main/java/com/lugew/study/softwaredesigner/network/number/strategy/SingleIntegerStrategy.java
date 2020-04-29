package com.lugew.study.softwaredesigner.network.number.strategy;

import java.util.Arrays;

/**
 * @author LuGew
 * @since 2020/4/28 23:51
 **/
public class SingleIntegerStrategy extends SingleStrategy<Integer> {
    private static final SingleIntegerStrategy instance = new SingleIntegerStrategy();

    private SingleIntegerStrategy() {
        this.bits = 32;
        this.chars = new char[this.bits];
    }

    public static SingleIntegerStrategy getInstance() {
        return instance;
    }

    @Override
    public String binary(Integer integer) {
        chars[0] = integer < 0 ? ONE : ZERO;

        int index = 31;
        while (integer != 0) {
            chars[index] = map.get(integer % 2);
            integer /= 2;
            index--;
        }
        zeroPadding(index);
        return Arrays.toString(chars);
    }
}
