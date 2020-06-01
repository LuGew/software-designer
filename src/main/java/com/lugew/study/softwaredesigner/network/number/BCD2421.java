package com.lugew.study.softwaredesigner.network.number;

/**
 * 2421BCD
 *
 * @author XINWEN95
 * @since 2020/6/1 23:26
 **/
public class BCD2421 extends AbstractBCD {
    @Override
    public int toDecimal(char[] binary) {
        ensureLegal(binary);
        return 2 * valueOfIndex(binary, 0) +
                4 * valueOfIndex(binary, 1) +
                2 * valueOfIndex(binary, 2) +
                valueOfIndex(binary, 3);
    }
}
