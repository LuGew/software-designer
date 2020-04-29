package com.lugew.study.softwaredesigner.network.number.strategy;


import java.util.Arrays;

/**
 * @author LuGew
 * @since 2020/4/28 23:51
 **/
public class SingleIntegerStrategy extends SingleStrategy<Integer> {
    private static final SingleIntegerStrategy instance = new SingleIntegerStrategy();

    private SingleIntegerStrategy() {
    }

    public static SingleIntegerStrategy getInstance() {
        return instance;
    }

    @Override
    public String binary(Integer integer) {
        StringBuilder result = new StringBuilder();
        int temp;
        while ((temp = integer / 2) != 0) {
            result.append(integer % 2);
            integer = temp;
        }
        return result.reverse().toString();
    }
}
