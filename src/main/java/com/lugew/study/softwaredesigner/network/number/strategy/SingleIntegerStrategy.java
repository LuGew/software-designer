package com.lugew.study.softwaredesigner.network.number.strategy;

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
    public char[] binary(Integer integer) {
        return twosComplement(integer);
    }

    @Override
    public char[] trueForm(Integer integer) {
        chars[0] = integer < 0 ? ONE : ZERO;
        int index = 31;
        while (integer != 0) {
            chars[index] = map.get(integer % 2);
            integer /= 2;
            index--;
        }
        zeroPadding(index);
        return chars;
    }

    @Override
    public char[] onesComplement(Integer integer) {
        char[] chars = trueForm(integer);
        for (int i = 1, length = chars.length; i < length; i++) {
            chars[i] = (chars[i] == ZERO ? ONE : ZERO);
        }
        return chars;
    }

    @Override
    public char[] twosComplement(Integer integer) {
        char[] chars = onesComplement(integer);
        for (int i = chars.length - 1; i > 0; i--) {
            if (chars[i] == ZERO) {
                chars[i] = ONE;
                break;
            } else {
                chars[i] = ZERO;
            }
        }
        return chars;
    }

    @Override
    public char[] offsetBinary(Integer integer) {
        return null;
    }


}
