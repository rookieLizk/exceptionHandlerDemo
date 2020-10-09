package com.exception.lizk.exception.advice;


import com.exception.lizk.exception.exception.ApiException;
import com.exception.lizk.exception.exception.BusinessException;
import com.exception.lizk.exception.exception.MyException;
import com.exception.lizk.exception.net.NetworkUtils;
import com.exception.lizk.exception.util.ExecptionResponseHandlerUtil;
import com.exception.lizk.exception.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常接收处理
 * @author <a href="mailto:sunmch@163.com">smc</a>
 * @date 2019-07-02 19:54
 * @since 1.0.0
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler  {
     
    @Autowired
    private MessageSource messageSource;
    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public ResponseEntity<Object> exceptionHandler(MyException ex, HttpServletRequest request){
        log.error("[MyException-REQUEST] api: {},ip:{},请求错误,{}",request.getRequestURI(), NetworkUtils.getIpAddress(request),ex.getMsg());
        try {
        	 return  new ResponseEntity<>(ex.toObject(), HttpStatus.valueOf(ex.getCode()) );
		} catch (Exception e) {
			 return  new ResponseEntity<>("service error", HttpStatus.valueOf(500));
		}
     }
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Result> handleGlobalExceptionResponse(Throwable e, HttpServletRequest request){
        log.error("[Exception-REQUEST] api: {},ip:{},请求错误,{}",request.getRequestURI(),NetworkUtils.getIpAddress(request),e.getMessage());
        return ExecptionResponseHandlerUtil.handleGlobalException(request,messageSource);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Result> handleApiExceptionResponse(ApiException apiException, HttpServletRequest request){
        log.error("[ApiException-REQUEST] api: {},ip:{},请求错误,{}",request.getRequestURI(),NetworkUtils.getIpAddress(request),apiException.getErrorKey());
        return ExecptionResponseHandlerUtil.handleApiException(request,messageSource,apiException);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Result> handleServiceExceptionResponse(BusinessException businessException, HttpServletRequest request){
        log.error("[BusinessException-REQUEST] api: {},ip:{},请求错误,{}",request.getRequestURI(),NetworkUtils.getIpAddress(request),businessException.getErrorKey());
        return ExecptionResponseHandlerUtil.handlBusinessException(request,messageSource,businessException);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Result> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        log.error("[MethodArgumentNotValidException-REQUEST] api: {},ip:{},请求错误,{}",request.getRequestURI(),NetworkUtils.getIpAddress(request),e.getMessage());
        String key = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ExecptionResponseHandlerUtil.handleApiException(request,messageSource,new ApiException(key));
    }


}
