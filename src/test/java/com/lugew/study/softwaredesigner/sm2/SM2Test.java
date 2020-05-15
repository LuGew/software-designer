package com.lugew.study.softwaredesigner.sm2;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;


/**
 * @author LuGew
 * @since 2020/5/15
 */
@Slf4j
public class SM2Test {

    private static final String privateKey = "b554013b2025b846400e6e4831b2e87df4fa73f03f124e3bd61b05fc9fa4ed11";
    private static final String publicKey = "0496a9d0daefa9da86da6f58cbe3e768eab2191fdb66d4cdad1e2282029b16efa542feb38da4b8ca95aa471d07001b7b1237f49750fc918fd0b0062c021af876c1";
//    private static final String privateKey = "308193020100301306072a8648ce3d020106082a811ccf5501822d047930770201010420a358dcca45b56c492afe992d23051279f6c72d23f0ce039bf20d3ff0347ace0ba00a06082a811ccf5501822da14403420004d94ce8de7dd80072f4c565b76ef906acaf7975f1372c49ffa4a8df9545180ca8735e814f6f16d1a123c5f14f768782916d9c410e7a60a310ccf13c32558bf986";
//    private static final String publicKey = "3059301306072a8648ce3d020106082a811ccf5501822d03420004d94ce8de7dd80072f4c565b76ef906acaf7975f1372c49ffa4a8df9545180ca8735e814f6f16d1a123c5f14f768782916d9c410e7a60a310ccf13c32558bf986";

    private static final String encryptData = "48b639f0b4ebac4dffd97a68d3ded78ab79466e7f0bb0a1b67ffd262c8812ed14fc9e33201d69c477205e7f45d17601d522f3169f45c201d94e1e25660ccacae88ae14a3232c20c0591fd92c3c8a649a9782eeb9f53088c376c82c4f5fa74d7fdeb55b7fea4e";

    @Test
    void test() {
        String text = "我是一段测试aaaa";

        SM2 sm2 = SmUtil.sm2();
//        SM2 sm2 = SmUtil.sm2(privateKey,publicKey);

        String priK = sm2.getPrivateKey().getFormat();
        String pubK = sm2.getPublicKey().getFormat();
        log.info("prik:{},pubK:{}", priK, pubK);
// 公钥加密，私钥解密
        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));
        System.out.println(decryptStr);
    }

    @Test
    void test1() {
        String text = "我是一段测试aaaa";

        KeyPair pair = SecureUtil.generateKeyPair("SM2");
//        byte[] privateKey = pair.getPrivate().getEncoded();
//        byte[] publicKey = pair.getPublic().getEncoded();
        log.info("privateKey:{},publicKey:{}", HexUtil.encodeHexStr(privateKey), HexUtil.encodeHexStr(publicKey));
        SM2 sm2 = SmUtil.sm2(HexUtil.decodeHex(privateKey), HexUtil.decodeHex(publicKey));
//        sm2.setMode(SM2Engine.Mode.C1C3C2);
// 公钥加密，私钥解密
        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));
        log.info("encryptStr:{},decryptStr:{}", encryptStr, decryptStr);
    }

    @Test
    void test2() {
        String text = "我是一段测试aaaa";

        KeyPair pair = SecureUtil.generateKeyPair("SM2");
        byte[] privateKey = pair.getPrivate().getEncoded();
        byte[] publicKey = pair.getPublic().getEncoded();
        log.info("privateKey:{},publicKey:{}", HexUtil.encodeHexStr(privateKey), HexUtil.encodeHexStr(publicKey));
     /*   SM2 sm2 = SmUtil.sm2(HexUtil.decodeHex(privateKey), HexUtil.decodeHex(publicKey));
        sm2.setMode(SM2Engine.Mode.C1C3C2);
// 公钥加密，私钥解密
        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));
        log.info("encryptStr:{},decryptStr:{}", encryptStr, decryptStr);*/
    }


}
