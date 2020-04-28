package com.lugew.study.softwaredesigner.network.number;

/**
 * @author XINWEN95
 * @since 2020/4/28 23:17
 **/
public abstract class AbstractCode implements Code {
    protected final char radix = 2;
    protected Number number;

    @Override
    public String binary() {
        if (number instanceof Integer) {
            number.getClass()
            return number.intValue();
        }

    }

    private void process() {

    }
}
