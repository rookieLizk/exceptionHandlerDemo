package com.exception.lizk.exception.util;
import java.time.LocalDateTime;

/**
 * 返回消息实体类
 * @author <a href="mailto:sunmch@163.com">smc</a>
 * @date 2019-07-02 18:57
 * @since 1.0.0
 **/
public class Result {

    /**
     * 默认成功编码
     */
    public static final String DEFAULT_SUCC_CODE="0";

    /**
     *  默认错误编码
     */
    public static final String DEFAULT_ERROR_CODE="500";

    /**
     * 默认成功返回消息
     */
    public static final String DEFAULT_SUCC_MSG="ok";

    /**
     * 默认错误返回消息
     */
    public static final String DEFAULT_ERROR_MSG="error";

    private String code;

    private String msg;

    private Object data;

    private LocalDateTime timestamp;

    public static Result succ(){
        return new Result(DEFAULT_SUCC_CODE,DEFAULT_SUCC_MSG,null);
    }

    public static Result succ(String code,String msg,Object data){
        return new Result(code,msg,data);
    }

    public static Result succ(String code,String msg){
        return new Result(code,msg,null);
    }

    public static Result succ(Object data){
        return new Result(DEFAULT_SUCC_CODE,DEFAULT_SUCC_MSG,data);
    }

    public static Result error(){
        return new Result(DEFAULT_ERROR_CODE,DEFAULT_ERROR_MSG,null);
    }

    public static Result error(String code,String msg,Object data){
        return new Result(code,msg,data);
    }

    public static Result error(String code,String msg){
        return new Result(code,msg,null);
    }

    public static Result error(Object data){
        return new Result(DEFAULT_ERROR_CODE,DEFAULT_ERROR_MSG,data);
    }

    public Result(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(){

    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }
}
