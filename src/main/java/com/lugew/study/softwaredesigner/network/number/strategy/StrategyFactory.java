package com.lugew.study.softwaredesigner.network.number.strategy;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XINWEN95
 * @since 2020/4/28 23:38
 **/
@UtilityClass
public class StrategyFactory {
    private final Map<String, Strategy> strategyMap = new HashMap<String, Strategy>(){{
        put("integer", SingleIntegerStrategy.getInstance());
    }};

    public Strategy getStrategy(String type) {
        return strategyMap.get(type);
    }

    public static void main(String[] args) {
        StrategyFactory.getStrategy("integer").binary(12345);
    }
}
