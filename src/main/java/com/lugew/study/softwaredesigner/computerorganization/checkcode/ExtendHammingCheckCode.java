package com.lugew.study.softwaredesigner.computerorganization.checkcode;

import com.google.common.primitives.Chars;

import java.util.Arrays;

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
    private CheckMethod extendCheckMethod = CheckMethod.EVEN;
    private char extendInformation = ZERO;

    public ExtendHammingCheckCode() {
        super();
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
        extendInformation = generateParityCheckCode(binary);
        char[] p0 = new char[]{
                extendInformation
        };

        if (isBigEndian()) {
            return Chars.concat(p0, result);
        } else {
            return Chars.concat(result, p0);
        }
    }


    @Override
    public char[] correct(char[] binary) {
        boolean e0 = check(binary);
        int errorIndex = getErrorPosition(Arrays.copyOfRange(binary, 1, binary.length));

        if (e0) {
            if (hasError(errorIndex)) {
                throw new RuntimeException("2 bits error");
            }
        } else {
            if (hasError(errorIndex)) {
                not(binary, errorIndex + 1);
            } else {
                not(binary, 0);
            }
        }
        return binary;
    }


    private boolean isExtendCheckEven() {
        return CheckMethod.EVEN.equals(this.extendCheckMethod);
    }

    private boolean isExtendCheckOdd() {
        return !isExtendCheckEven();
    }

    @Override
    public boolean check(char[] binary) {
        ensureLegal(binary);
        return isParityPass(xor(binary));
    }

    @Override
    protected boolean isParityPass(byte xorResult) {
        return (isExtendCheckEven() && xorResult == 0) || (isExtendCheckOdd() && xorResult == 1);
    }

    @Override
    protected char generateParityCheckCode(char[] binary) {
        byte xorResult = xor(binary);
        if (isExtendCheckOdd()) {
            xorResult = not(xorResult);
        }
        return map.inverse().get(xorResult);
    }
}
