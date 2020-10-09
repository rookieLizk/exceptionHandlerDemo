package com.exception.lizk.exception.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 基础工具类
 * @author lizk
 * @date 2019-07-03 13:16
 * @since 1.0.0
 **/
public class BaseUtil {


    private static  final Logger log = LoggerFactory.getLogger(BaseUtil.class);

//    private static final ThreadLocal<UserCacheInfo> localUserInfo = new ThreadLocal();
//
//
//    public static void setCurrentUser(UserCacheInfo userCacheInfo){
//        localUserInfo.set(userCacheInfo);
//    }
//
//    public static void removeCurrentUser(){
//        localUserInfo.remove();
//    }
//    /**
//     * 获取当前用户信息
//     */
//    public static UserCacheInfo currentUser(){
//        return localUserInfo.get();
//    }
    /**
     * @see BaseUtil#str2Int(String, int)
     */
    public static int str2Int(String source){
        return str2Int(source,0);
    }

    /**
     * 字符串转整型如转换异常使用默认值
     */
    public static int str2Int(String source,int defautValue){
        int target = defautValue;
        try{
            target = Integer.parseInt(source);
        }catch (Exception ex){
            log.error("source:{}转换整型错误: {},返回默认值: {}",source,ex.getMessage(),defautValue);
        }
        return target;
    }

    /**
     * 验证source是否为空,如果为空返回默认值
     */
    public static String resNotBlank(String source,String defaultStr){
        String target = defaultStr;
        if (!StringUtils.isEmpty(source)) {
            target = source;
        }
        return target;
    }

    public static  <T> T getTargetObjByClass(Class<T> targetClass){
        try {
            T targetObj = targetClass.newInstance();
            return targetObj;
        } catch (InstantiationException e) {
            log.error("目标class: {}反射对象失败:{}",targetClass.getName(),e.getMessage());
        } catch (IllegalAccessException e) {
            log.error("目标class: {}IllegalAccessException-反射对象失败:{}",targetClass.getName(),e.getMessage());
        }
        return null;
    }


}
