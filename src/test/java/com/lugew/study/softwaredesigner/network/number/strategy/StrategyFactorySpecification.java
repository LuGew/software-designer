package com.lugew.study.softwaredesigner.network.number.strategy;

import com.lugew.study.softwaredesigner.network.number.exception.StrategyNotFoundException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * @author LuGew
 * @since 2020/5/17
 */
public class StrategyFactorySpecification {

    @Test
    void whenNotExistStrategyThenThrowStrategyNotFoundException() {
        Throwable throwable = catchThrowable(() -> {
            StrategyFactory.getStrategy("Integer1");
        });
        assertThat(throwable).isInstanceOf(StrategyNotFoundException.class);

    }

    @Test
    void whenExistStrategyThenReturnStrategy() {
        assertThat(StrategyFactory.getStrategy("Integer")).isInstanceOf(Strategy.class);
    }
}