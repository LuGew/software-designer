package com.lugew.study.softwaredesigner.network.number.strategy;


/**
 * @author XINWEN95
 * @since 2020/4/28 23:51
 **/
public abstract class BaseStrategy<T extends Number> extends AbstractStrategy<T> {

    protected void zeroPadding(int start) {
        for (int i = start; i > 0; i--) {
            chars[i] = ZERO;
        }
    }
}
