package com.lugew.study.softwaredesigner.network.number.strategy;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author LuGews
 * @since 2020/5/10 9:45
 **/
public class GenericIntegerStrategy<T> extends BaseStrategy<Long> {


    public GenericIntegerStrategy(int bits) {

        this.bits = bits;
        this.chars = new char[this.bits];
    }

    public GenericIntegerStrategy() {
        bits = 32;

        Type type = this.getClass().getGenericSuperclass();
        Class clazz = (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
        if (clazz.equals(Integer.class)) {
            bits = 32;
        } else if (clazz.equals(Long.class)) {
            bits = 64;
        } else if (clazz.equals(Byte.class)) {
            bits = 8;
        } else if (clazz.equals(Short.class)) {
            bits = 16;
        } else {
            throw new RuntimeException();
        }
        this.chars = new char[this.bits];
    }


    @Override
    public char[] binary(Long aLong) {
        return twosComplement(aLong);
    }

    @Override
    public char[] trueForm(Long aLong) {
        chars[0] = aLong < 0 ? ONE : ZERO;
        int index = bits - 1;
        while (aLong != 0) {
            chars[index] = map.get(aLong % 2);
            aLong /= 2;
            index--;
        }
        zeroPadding(index);
        return chars;
    }

    @Override
    public char[] onesComplement(Long aLong) {
        char[] chars = trueForm(aLong);
        if (aLong < 0) {
            for (int i = 1, length = chars.length; i < length; i++) {
                chars[i] = (chars[i] == ZERO ? ONE : ZERO);
            }
        }
        return chars;
    }

    @Override
    public char[] twosComplement(Long aLong) {
        char[] chars = onesComplement(aLong);
        if (aLong < 0) {
            for (int i = chars.length - 1; i > 0; i--) {
                if (chars[i] == ZERO) {
                    chars[i] = ONE;
                    break;

                } else {
                    chars[i] = ZERO;
                }
            }
        }
        return chars;
    }

    @Override
    public char[] offsetBinary(Long aLong) {
        char[] chars = twosComplement(aLong);
        chars[0] = chars[0] == ZERO ? ONE : ZERO;
        return chars;
    }

}
