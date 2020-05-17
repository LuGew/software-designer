package com.lugew.study.softwaredesigner.core.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @author LuGew
 * @since 2020/5/17
 */
@Slf4j
public class Exception extends RuntimeException {
    public Exception() {
        super();
    }

    public Exception(String message, Object... objects) {
        super(message);
        log.info(message, objects);
    }

    public Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public Exception(Throwable cause) {
        super(cause);
    }

    protected Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
