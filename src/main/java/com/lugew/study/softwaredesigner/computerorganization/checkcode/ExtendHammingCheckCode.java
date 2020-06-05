package com.lugew.study.softwaredesigner.computerorganization.checkcode;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

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
    public static BiMap<Character, Integer> map = HashBiMap.create(2);
    private Method extendMethod = Method.EVEN;
    private Endian extendEndian = Endian.BIG;

    public ExtendHammingCheckCode() {
    }

    public ExtendHammingCheckCode(int informationBits) {
        super(informationBits);
    }

    public Method getExtendMethod() {
        return extendMethod;
    }

    public void setExtendMethod(Method extendMethod) {
        this.extendMethod = extendMethod;
    }

    @Override
    public char[] generate(char[] binary) {
        char[] result = super.generate(binary);
        char p0 = getCheckCode(binary);
        if (isExtendEndianBig()) {
            return p0 + result;
        } else {
            return result + p0;
        }
    }

    private boolean isExtendMethodEven() {
        return Method.EVEN.equals(this.extendMethod);
    }

    private boolean isExtendEndianBig() {
        return Endian.BIG.equals(this.extendEndian);
    }

    private char getCheckCode(char[] binary) {
        int temp = 0;
        for (char c : binary) {
            temp ^= map.get(c);
        }
        if (!isExtendMethodEven()) {
            temp ^= 1;
        }
        return map.inverse().get(temp);
    }
}
