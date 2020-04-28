package com.lugew.study.softwaredesigner.network.number.strategy;


/**
 * @author XINWEN95
 * @since 2020/4/28 23:51
 **/
public class SingleIntegerStrategy extends SingleStrategy<Integer> {
    private static final SingleIntegerStrategy instance = new SingleIntegerStrategy();

    private SingleIntegerStrategy() {
    }

    @Override
    public String binary(Integer integer) {
        return null;
    }
}
