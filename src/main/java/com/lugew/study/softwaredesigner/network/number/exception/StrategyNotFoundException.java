package com.lugew.study.softwaredesigner.network.number.exception;

import com.lugew.study.softwaredesigner.core.exception.Exception;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LuGew
 * @since 2020/5/17
 */
@Slf4j
public class StrategyNotFoundException extends Exception {
    public StrategyNotFoundException() {

    }

    public StrategyNotFoundException(String message, Object... objects) {
        super(message, objects);
    }
}
