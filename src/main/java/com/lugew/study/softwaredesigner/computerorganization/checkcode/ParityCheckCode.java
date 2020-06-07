package com.lugew.study.softwaredesigner.computerorganization.checkcode;

/**
 * 奇偶校验码
 * 奇偶校验只能发现一位或者奇数位错，无法发现偶数位错，即使发现错误也无法纠错
 * 一位出错的概率高，能够满足一般可靠性要求，
 * 在
 * {@link com.lugew.study.softwaredesigner.network.CPU}
 * 与
 * {@link com.lugew.study.softwaredesigner.network.Memory}
 * 的信息传输中广泛应用
 *
 * @author LuGew
 * @since 2020/6/2
 */
public class ParityCheckCode extends AbstractCheckCode {
    private CheckMethod checkMethod = CheckMethod.EVEN;
    private Endian endian = Endian.BIG;

    @Override
    public char[] generate(char[] binary) {
        ensureLegal(binary);
        int length = binary.length;
        int copyIndexOfResult = 1;
        int checkIndex = 0;
        char[] result = new char[length + 1];
        if (isLittleEndian()) {
            copyIndexOfResult = 0;
            checkIndex = length;
        }
        System.arraycopy(binary, 0, result, copyIndexOfResult, length);
        result[checkIndex] = generateParityCheckCode(binary);
        return result;
    }

    @Override
    public boolean check(char[] binary) {
        ensureLegal(binary);
        return isParityPass(xor(binary));
    }

    /**
     * 异或
     *
     * @param binary 输入字符串
     * @return 异或结果
     */
    protected byte xor(char[] binary) {
        byte temp = 0;
        for (char c : binary) {
            temp ^= map.get(c);
        }
        return temp;
    }

    protected byte not(byte input) {
        input ^= 1;
        return input;
    }

    protected char generateParityCheckCode(char[] binary) {
        byte XORResult = xor(binary);
        if (isOddCheck()) {
            XORResult = not(XORResult);
        }
        return map.inverse().get(XORResult);
    }

    protected boolean isEvenCheck() {
        return getCheckMethod().equals(CheckMethod.EVEN);
    }

    protected boolean isOddCheck() {
        return !isEvenCheck();
    }

    protected boolean isParityPass(byte xorResult) {
        return (isEvenCheck() && xorResult == 0) || (isOddCheck() && xorResult == 1);
    }

    protected boolean isOddNumber(int number) {
        return !isEvenNumber(number);
    }

    protected boolean isEvenNumber(int number) {
        return number % 2 == 0;
    }

    public CheckMethod getCheckMethod() {
        return checkMethod;
    }

    public void setCheckMethod(CheckMethod checkMethod) {
        this.checkMethod = checkMethod;
    }

    public void setEndian(Endian endian) {
        this.endian = endian;
    }

    public boolean isBigEndian() {
        return this.endian.equals(Endian.BIG);
    }

    public boolean isLittleEndian() {
        return !isBigEndian();
    }


    public enum CheckMethod {
        ODD,
        EVEN
    }

    public enum Endian {
        BIG,
        LITTLE
    }
}
