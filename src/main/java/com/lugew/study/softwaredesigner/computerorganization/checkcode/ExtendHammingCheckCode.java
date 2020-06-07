package com.lugew.study.softwaredesigner.computerorganization.checkcode;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.primitives.Chars;

/**
 * 海明校验码只能检1位错，纠1位错
 * 扩展海明校验码可以检2位错，或者纠正1位错
 * <p>
 * 增加一位校验位P，构成长度位n+k+1的编码
 * P使之构成奇/偶（数）校验
 *
 * @author LuGew
 * @since 2020/6/2
 */
public class ExtendHammingCheckCode extends HammingCheckCode {
    public BiMap<Character, Byte> map = HashBiMap.create(2);
    private CheckMethod extendCheckMethod = CheckMethod.EVEN;

    public ExtendHammingCheckCode() {
        super();
        this.map.put('0', (byte) 0b0);
        this.map.put('1', (byte) 0b1);
    }

    public ExtendHammingCheckCode(int informationBits) {
        super(informationBits);
    }

    public CheckMethod getExtendCheckMethod() {
        return extendCheckMethod;
    }

    public void setExtendCheckMethod(CheckMethod extendCheckMethod) {
        this.extendCheckMethod = extendCheckMethod;
    }

    public void enableOddExtendMethod() {
        setExtendCheckMethod(CheckMethod.ODD);
    }

    public void enableLittleEndian() {
        setEndian(Endian.LITTLE);
    }

    @Override
    public char[] generate(char[] binary) {
        char[] result = super.generate(binary);
        char[] p0 = new char[]{
                getCheckCode(binary)
        };

        if (isBigEndian()) {
            return Chars.concat(p0, result);
        } else {
            return Chars.concat(result, p0);
        }
    }


    @Override
    public boolean check(char[] checkCode) {
        byte result = XOR(checkCode);

        if (!isExtendMethodEven()) {
            result ^= 1;
        }
        return result == 0;
    }

    @Override
    public char[] correct(char[] binary) {
        boolean e0 = validated(binary);
//        if (e0) {
//            super.check(Arrays.copyOfRange(binary,))
//        }
        return null;
    }

    private boolean isExtendMethodEven() {
        return CheckMethod.EVEN.equals(this.extendCheckMethod);
    }

    private char getCheckCode(char[] binary) {
        byte temp = XOR(binary);
        if (!isExtendMethodEven()) {
            temp ^= 0b1;
        }
        return map.inverse().get(temp);
    }

    private boolean validated(char[] binary) {
        byte result = XOR(binary);
        if (!isExtendMethodEven()) {
            result ^= 0;
        }
        return result == 0;
    }

    private byte XOR(char[] binary) {
        byte temp = 0;
        for (char c : binary) {
            temp ^= map.get(c);
        }
        return temp;
    }
}
