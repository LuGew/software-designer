package com.lugew.study.softwaredesigner.network.number;

/**
 * @author XINWEN95
 * @since 2020/6/1 22:40
 **/
public abstract class AbstractBCD implements BCD {
    @Override
    public int toDecimal(char[] binary) {
        ensureLegal(binary);
        return 8 * valueOfIndex(binary, 0) +
                4 * valueOfIndex(binary, 1) +
                2 * valueOfIndex(binary, 2) +
                valueOfIndex(binary, 3);
    }

    protected void ensureLegal(char[] binary) {
        if (null == binary) {
            throw new RuntimeException("content can't be null");
        } else if (binary.length > 4) {
            throw new RuntimeException("length can't more than 4");
        } else if (binary.length < 4) {
            throw new RuntimeException("length can't less than 4");
        }
    }

    protected int valueOfIndex(char[] binary, int index) {
        return binary[index] - '0';
    }
}
