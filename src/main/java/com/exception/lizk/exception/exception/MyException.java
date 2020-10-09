package com.exception.lizk.exception.exception;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * ApiException
 * @author lizk
 * 举个例子
 * throw new ApiException("对象不存在"); -->默认1000，"a对象不存在"会返回给用户
 * throw new ApiException(401,"global.token.invalid","token不可用"); -->返回401和自定义信息
 * ...更多见接口 
 * 400 - 请求无效;401 - 未授权;403 - 禁止访问;
 * 500 - 内部服务器错误;
 */
@Data
public class MyException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	/**
	 * 异常状态码
	 */
	private int code=200; 
	/**
	 * 异常对应的键
	 */
    private String key;   
    /**
     * 异常信息
     */
    private String msg;  
    /**
     * 返回信息
     */
    private Object data; 
    /**
     * 时间
     */
    public MyException(String key, String msg) {
    	 this.msg=msg;
         this.key=key;
    }
    public MyException(String msg) {
        this.msg=msg;
   }
    public MyException(int code) {
        this.code=code;
    }
    public MyException(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }
    public MyException(int code, String key, String msg) {
        this.code=code;
        this.msg=msg;
        this.key=key;
    }
    public MyException(String msg, Exception e) {
        this.msg=msg;
        this.data=e.getMessage();
    }
    public MyException(int code, String key, String msg, Object data) {
        this.code=code;
        this.msg=msg;
        this.key=key;
        this.data=data;
    }
    public Map<String ,Object> toObject() {
    	Map<String ,Object> map=new HashMap<>();
    	map.put("code", "1");
    	if(this.key!=null) {map.put("key", this.key);}
    	if(this.msg!=null) {map.put("msg", this.msg);}
        if(this.data!=null) {map.put("data", this.data);}
        map.put("timestamp", LocalDateTime.now().toString());
    	return map;
    }
    public String getInfo() {
    	Map<String ,Object> map=new HashMap<>();
    	map.put("code", this.code);
    	map.put("key", this.key);
    	map.put("msg", this.msg);
        map.put("data", this.data);
        map.put("timestamp", LocalDateTime.now().toString());
    	return map.toString();
    }
}
