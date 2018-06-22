package com.treasure.hunt.framework.exception;

/**
 * @author linying
 * @version v1.0.0
 * @Description 业务异常
 * @Date 2018-04-13 11:15:25
 * @modified By
 */
public class BusinessException extends Exception {
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression,
                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
