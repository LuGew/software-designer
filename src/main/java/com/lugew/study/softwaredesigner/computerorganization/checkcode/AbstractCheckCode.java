package com.lugew.study.softwaredesigner.computerorganization.checkcode;

/**
 * 抽象校验码
 *
 * @author LuGew
 * @since 2020/6/2
 */
public abstract class AbstractCheckCode implements CheckCode {
    protected void ensureLegal(char[] binary) {
        if (null == binary) {
            throw new RuntimeException("input is null");
        }
        for (char temp : binary) {
            if (map.get(temp) == null) {
                throw new RuntimeException("input not binary");
            }
        }

    }

    @Override
    public char[] generate(char[] binary) {
        ensureLegal(binary);
        return null;
    }

    @Override
    public boolean check(char[] binary) {
        return true;
    }

    @Override
    public char[] correct(char[] binary) {
        return new char[0];
    }


}
