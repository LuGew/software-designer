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
    /**
     * 校验方式
     */
    private CheckMethod checkMethod = CheckMethod.EVEN;
    /**
     * 大端或者小端
     */
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

    /**
     * 根据源二进制数据生成P
     *
     * @param binary 源二进制数据
     * @return P
     */
    protected char generateParityCheckCode(char[] binary) {
        byte xorResult = xor(binary);
        if (isOddCheck()) {
            xorResult = not(xorResult);
        }
        return map.inverse().get(xorResult);
    }


    protected boolean isEvenCheck() {
        return getCheckMethod().equals(CheckMethod.EVEN);
    }


    protected boolean isOddCheck() {
        return !isEvenCheck();
    }

    /**
     * E校验通过
     *
     * @param xorResult 异或值
     * @return 结果
     */
    protected boolean isParityPass(byte xorResult) {
        return (isEvenCheck() && xorResult == 0) || (isOddCheck() && xorResult == 1);
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
