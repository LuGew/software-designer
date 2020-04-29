package com.lugew.study.softwaredesigner.network.number.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XINWEN95
 * @since 2020/4/28 23:37
 **/
public abstract class AbstractStrategy<T extends Number> implements Strategy<T> {
    protected final char ZERO = '0';
    protected final char ONE = '1';
    protected int bits;
    protected char[] chars;
    protected Map<Integer, Character> map = new HashMap<Integer, Character>() {{
        put(1, ONE);
        put(0, ZERO);
        put(-1, ONE);
    }};


}
