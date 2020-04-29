package com.lugew.study.softwaredesigner.network.number;

import com.lugew.study.softwaredesigner.network.number.strategy.StrategyFactory;

/**
 * @author XINWEN95
 * @since 2020/4/28 23:17
 **/
public abstract class AbstractCode implements Code {
    protected Number number;

    @Override
    public String binary() {
        return StrategyFactory.getStrategy(number.getClass().getSimpleName()).binary(number);
    }

}
