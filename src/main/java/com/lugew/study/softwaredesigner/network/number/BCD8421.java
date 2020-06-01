package com.lugew.study.softwaredesigner.network.number;

/**
 * 8421BCD
 *
 * @author XINWEN95
 * @since 2020/6/1 22:42
 **/
public class BCD8421 extends AbstractBCD {
    @Override
    public int toDecimal(char[] binary) {
        ensureLegal(binary);
        return 8 * valueOfIndex(binary, 0) +
                4 * valueOfIndex(binary, 1) +
                2 * valueOfIndex(binary, 2) +
                valueOfIndex(binary, 3);
    }


}
