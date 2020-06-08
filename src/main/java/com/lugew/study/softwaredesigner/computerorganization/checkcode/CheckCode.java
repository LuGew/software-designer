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


    /**
     * 由源数据生成校验码
     *
     * @param binary 原始二进制数据
     * @return 根据规则生成的校验码
     */
    char[] generate(char[] binary);

    /**
     * 校验校验码是否通过
     *
     * @param binary 校验码
     * @return 是否通过
     */
    boolean check(char[] binary);

    /**
     * 纠错
     *
     * @param binary 校验码
     * @return 纠错之后的校验码
     */
    char[] correct(char[] binary);
}
