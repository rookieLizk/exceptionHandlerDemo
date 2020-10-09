package com.exception.lizk.exception.exception;

import lombok.Data;

/**
 * @author lizk
 * @date 2019-07-11 14:57
 * @since
 **/
@Data
public class BaseException extends RuntimeException {

    private String errorKey;

    private Object[] values;

    public BaseException(String errorKey, Object... values) {
        this.errorKey = errorKey;
        this.values = values;
    }
}
