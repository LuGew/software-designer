package com.lugew.study.softwaredesigner.network.number;

/**
 * @author XINWEN95
 * @since 2020/6/1 23:45
 **/
public class BCDSubtract3 extends AbstractBCD {
    @Override
    public int toDecimal(char[] binary) {
        return super.toDecimal(binary) - 3;
    }
}
