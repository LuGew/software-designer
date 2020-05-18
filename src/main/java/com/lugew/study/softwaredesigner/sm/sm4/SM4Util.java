package com.lugew.study.softwaredesigner.sm.sm4;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.json.JSONUtil;

/**
 * @author LuGew
 * @since 2020/5/18
 */
public class SM4Util {
    private static final String KEY = "D712A38E50FE785081919955CB09F817";
    private static final SymmetricCrypto SYMMETRIC_CRYPTO = SmUtil.sm4(HexUtil.decodeHex(KEY));

    public static String encrypt(String content) {

        return SYMMETRIC_CRYPTO.encryptHex(content);
    }

    public static String decrypt(String encryptHex) {
        return SYMMETRIC_CRYPTO.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
    }

    public static void main(String[] args) {
//        JSONUtil.
        String content = "123456";
        String encrypt = SM4Util.encrypt(content);
        System.out.println(SM4Util.decrypt(encrypt));
    }


}
