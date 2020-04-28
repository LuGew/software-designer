package com.lugew.study.softwaredesigner.network.number.strategy;

/**
 * @author XINWEN95
 * @since 2020/4/28 23:37
 **/
public interface Strategy<T extends Number> {
    String binary(T t);
}
