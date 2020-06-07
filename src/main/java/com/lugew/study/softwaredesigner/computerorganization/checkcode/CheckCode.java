package com.lugew.study.softwaredesigner.computerorganization.checkcode;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.HashMap;

/**
 * 校验码
 *
 * @author LuGew
 * @since 2020/6/2
 */
public interface CheckCode {
    char ONE = '1';
    char ZERO = '0';
    BiMap<Character, Byte> map = HashBiMap.create(new HashMap<Character, Byte>() {{
        put('1', (byte) 1);
        put('0', (byte) 0);
    }});


    char[] generate(char[] binary);

    boolean check(char[] binary);

    char[] correct(char[] binary);
}
