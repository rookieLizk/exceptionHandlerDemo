package com.exception.lizk.exception.util;

import com.exception.lizk.exception.exception.ApiException;
import com.exception.lizk.exception.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * 处理异常结果信息
 * 返回消息根据国际化配置文件中的内容进行
 * 暂时系统返回消息不牵涉国际化如果需要切换
 * {@link HttpServletRequest}
 * {@link MessageSource}
 *
 * @author <a href="mailto:sunmch@163.com">smc</a>
 * @date 2019-07-02 19:39
 * @see HttpServletRequest#getLocale()
 * @see MessageSource#getMessage(String, Object[], Locale)
 * @since 1.0.0
 **/
public class ExecptionResponseHandlerUtil {


    private static final Logger log = LoggerFactory.getLogger(ExecptionResponseHandlerUtil.class);

    private static final String MSG_SEPARATOR = ";";

    /**
     * 系统默认错误编码
     */
    private static final String RES_DEFAULT_ERROR_CODE = "500";

    /**
     * 系统默认错误状态
     */
    private static int RES_DEFAULT_ERROR_STATUS = 500;

    /**
     * 全局默认的返回错误信息
     */
    private static final String RES_DEFAULT_ERROR_MSG = "global.ServiceInternalError";

    /**
     * 全局默认返回错误key
     */
    private static final String RES_DEFAULT_ERROR_KEY = RES_DEFAULT_ERROR_MSG;

    /**
     * 没有定义的异常
     */
    private static final String RES_UNKNOWN_ERROR_MSG = "system.unknown";

    /**
     * 处理全局没有捕获的异常
     * {@link com.yxt.web.aspect.ServiceRequestAspect}  Throwable ex 异常
     * {@link com.yxt.web.advice.GlobalExceptionHandler} 全局异常接收处理
     */
    public static ResponseEntity<Result> handleGlobalException(HttpServletRequest request, MessageSource msgSource) {
        String msgValue = getDefaultErrorMsg(RES_DEFAULT_ERROR_KEY);
        try {
            msgValue = msgSource.getMessage(RES_DEFAULT_ERROR_KEY, null, null);
        } catch (Exception ex) {
            log.error("解析key:{} 全局异常错误表失败:{}", RES_DEFAULT_ERROR_KEY, ex.getMessage());
        }
        return executionResponseEntity(msgValue);
    }

    public static ResponseEntity<Result> handleApiException(HttpServletRequest request, MessageSource msgSource, ApiException apiException) {
        String msgValue = getDefaultErrorMsg(apiException.getErrorKey());;
        try {
            msgValue = msgSource.getMessage(apiException.getErrorKey(), apiException.getValues(), null);
        } catch (Exception ex) {
            log.error("ApiException-解析key:{} 异常错误表失败:{}", apiException.getErrorKey(), ex.getMessage());
        }
        return executionResponseEntity(msgValue);
    }

    public static ResponseEntity<Result> handlBusinessException(HttpServletRequest request, MessageSource msgSource, BusinessException businessException) {
        String msgValue= getDefaultErrorMsg(businessException.getErrorKey());
        try {
            msgValue = msgSource.getMessage(businessException.getErrorKey(), businessException.getValues(), null);
        } catch (Exception ex) {
            log.error("BusinessException-解析key:{} ApiException-异常错误表失败:{}", businessException.getErrorKey(), ex.getMessage());
        }
        return executionResponseEntity(msgValue);
    }

    private static ResponseEntity<Result> executionResponseEntity(String msgValue) {

        ResponseEntity<Result> resultResponseEntity = null;
        try {
            String[] values = msgValue.split(MSG_SEPARATOR);
            if (values.length < 3) {
                values = new String[]{String.valueOf(RES_DEFAULT_ERROR_STATUS), RES_UNKNOWN_ERROR_MSG, RES_DEFAULT_ERROR_CODE};
            }
            resultResponseEntity = new ResponseEntity<>(Result.error(BaseUtil.resNotBlank(values[2], RES_DEFAULT_ERROR_CODE), BaseUtil.resNotBlank(values[1], RES_DEFAULT_ERROR_MSG)), HttpStatus.valueOf(BaseUtil.str2Int(values[0], RES_DEFAULT_ERROR_STATUS)));
        } catch (Exception ex) {
            log.error("解析异常错误表失败", ex);
            resultResponseEntity = new ResponseEntity<>(Result.error(RES_DEFAULT_ERROR_CODE, RES_DEFAULT_ERROR_MSG), HttpStatus.valueOf(RES_DEFAULT_ERROR_STATUS));
        }
        return resultResponseEntity;

    }

    private static String getDefaultErrorMsg(String errorMsg){
        return RES_DEFAULT_ERROR_STATUS+MSG_SEPARATOR+errorMsg+MSG_SEPARATOR+RES_DEFAULT_ERROR_CODE;
    }


}
