package com.lugew.study.softwaredesigner.network.number.strategy;

import com.lugew.study.softwaredesigner.network.number.exception.StrategyNotFoundException;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XINWEN95
 * @since 2020/4/28 23:38
 **/
@UtilityClass
public class StrategyFactory {
    private final Map<String, Strategy> strategyMap = new HashMap<String, Strategy>() {{
        put("Integer", new IntegerStrategy());
        put("Long", new LongStrategy());
        put("Short", new ShortStrategy());
        put("Byte", new ByteStrategy());
    }};

    public Strategy getStrategy(String type) {
        Strategy strategy = strategyMap.get(type);
        if (null == strategy) {
            throw new StrategyNotFoundException();
        }
        return strategy;
    }

}
