package com.exception.lizk.exception.exception;

/**
 * 业务层异常类
 * @author lizk
 * @date 2019-07-02 18:49
 * @since 1.0.0
 **/
public class BusinessException extends BaseException{


    public BusinessException(String errorKey, Object... values) {
         super(errorKey,values);
    }

    public BusinessException(String errorKey) {
        this(errorKey, new Object[0]);
    }


}
