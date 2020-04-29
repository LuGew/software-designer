package com.lugew.study.softwaredesigner.network.number;

/**
 * @author LuGew
 * @since 2020/4/28 22:50
 **/
public class OriginalCode extends AbstractCode {
    public OriginalCode(Number number) {
        this.number = number;
    }

    public static void main(String[] args) {
        System.out.println(new OriginalCode(-123).binary());
    }
}
