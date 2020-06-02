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
    private Method method = Method.EVEN;
    private Endian endian = Endian.BIG;

    public void setMethod(Method method) {
        this.method = method;
    }

    public Method getMethod() {
        return method;
    }

    public void setEndian(Endian endian) {
        this.endian = endian;
    }

    @Override
    public char[] generate(char[] binary) {
        ensureLegal(binary);
        int code = 0;
        char[] result = new char[binary.length + 1];
        if (this.endian.equals(Endian.LITTLE)) {
            for (int i = 0; i < binary.length; i++) {
                char temp = binary[i];
                result[i] = temp;
                if (temp == ONE) {
                    code++;
                } else if (temp == ZERO) {

                } else {
                    throw new RuntimeException("input not binary");
                }
            }
            result[binary.length] = code % 2 == 0 ? ZERO : ONE;
            if (this.method.equals(Method.ODD)) {
                result[binary.length] = result[binary.length] == ZERO ? ONE : ZERO;
            }
        } else {

            for (int i = binary.length - 1; i >= 0; i--) {
                char temp = binary[i];
                result[i + 1] = temp;
                if (temp == ONE) {
                    code++;
                } else if (temp == ZERO) {

                } else {
                    throw new RuntimeException("input not binary");
                }
            }
            result[0] = code % 2 == 0 ? ZERO : ONE;
            if (this.method.equals(Method.ODD)) {
                result[0] = result[0] == ZERO ? ONE : ZERO;
            }
        }
        return result;
    }

    @Override
    public boolean check(char[] checkCode) {
        ensureLegal(checkCode);
        int code = 0;
        for (char temp : checkCode) {
            if (temp == ONE) {
                code++;
            } else if (temp == ZERO) {

            } else {
                throw new RuntimeException("input not binary");
            }
        }
        if (this.method.equals(Method.EVEN)) {
            return 0 == code % 2;
        } else {
            return 1 == code % 2;
        }

    }

    protected boolean isChecked(int oneCount) {
        return (isEven(oneCount) && isEvenMode()) || (isOdd(oneCount) && isOddMode());
    }

    protected boolean isEvenMode() {
        return getMethod().equals(ParityCheckCode.Method.EVEN);
    }

    protected boolean isOddMode() {
        return !isEvenMode();
    }


    protected boolean isOdd(int number) {
        return !isEven(number);
    }

    protected boolean isEven(int number) {
        return number % 2 == 0;
    }

    public enum Method {
        ODD,
        EVEN
    }

    public enum Endian {
        BIG,
        LITTLE
    }
}
