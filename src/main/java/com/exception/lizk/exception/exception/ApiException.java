package com.exception.lizk.exception.exception;

/**
 * api层面异常消息
 * @author lizk
 * @date 2019-07-02 18:50
 * @since 1.0.0
 **/
public class ApiException extends BaseException {


    public ApiException(String errorKey, Object... values) {
         super(errorKey,values);
    }

    public ApiException(String errorKey) {
        this(errorKey, new Object[0]);
    }

}
