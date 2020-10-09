package com.exception.lizk.exception.exception;


import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * 验证断言的 {@link Objects}
 * @author lizk
 * @date 2019-07-04 10:15
 * @since 1.0.0
 **/
public class AssertHelper {


    public static void isNull(Object source, String errorKey){
        isNull(source,errorKey,"");
    }

    public static void isNull(Object source, String errorKey,String... errorValues) {
        if (Objects.nonNull(source)) {
            throw new BusinessException(errorKey,errorValues);
        }
    }

    public static void notNull(Object source, String errorKey){
        notNull(source,errorKey,"");
    }

    public static void notNull(Object source, String errorKey,String... errorValues) {
        if (Objects.isNull(source)) {
            throw new BusinessException(errorKey,errorValues);
        }
    }

    public static void isTure(Boolean source, String errorKey){
        isTure(source,errorKey,"");
    }

    public static void isTure(Boolean source, String errorKey,String... errorValues) {
        if (!source) {
            throw new BusinessException(errorKey,errorValues);
        }
    }

    public static void notBlank(String source, String errorKey){
        notBlank(source,errorKey,"");
    }
    public static void notBlank(String source, String errorKey,String... errorValues) {
        if (source == null || source.isEmpty()) {
            throw new BusinessException(errorKey,errorValues);
        }
    }

    public static void notEmpty(Collection<?> source, String errorKey){
        notEmpty(source,errorKey,"");
    }

    public static void notEmpty(Collection<?> source, String errorKey,String... errorValues) {
        if (source == null || source.isEmpty()) {
            throw new BusinessException(errorKey,errorValues);
        }
    }

    public static void notEmpty(Map<?, ?> source, String errorKey){
        notEmpty(source,errorKey,"");
    }

    public static void notEmpty(Map<?, ?> source, String errorKey,String... errorValues) {
        if (source == null || source.isEmpty()) {
            throw new BusinessException(errorKey,errorValues);
        }
    }

    public static void notNull(Collection<?> source, String errorKey){
        notNull(source,errorKey,"");
    }

    public static void notNull(Collection<?> source, String errorKey,String... errorValues) {
        if (source == null) {
            throw new BusinessException(errorKey,errorValues);
        }
    }

    public static void notNull(Map<?, ?> source, String errorKey){
        notNull(source,errorKey,"");
    }
    public static void notNull(Map<?, ?> source, String errorKey,String... errorValues) {
        if (source == null) {
            throw new BusinessException(errorKey,errorValues);
        }
    }

    public static void isEquals(String target,String source,String errorKey){
        isEquals(target,source,errorKey,"");
    }

    public static void isEquals(String target,String source,String errorKey,String... errorValues){
        isEquals(target,source,false,errorKey);
    }

    public static void isEquals(String target,String source,boolean ignoreCase,String errorKey){
        isEquals(target,source,ignoreCase,errorKey,"");
    }

    public static void isEquals(String target,String source,boolean ignoreCase,String errorKey,String... errorValues){
        if (ignoreCase){
            target = target.toLowerCase();
            source = source.toLowerCase();
        }
        if (!target.equals(source)){
            throw new BusinessException(errorKey,errorValues);
        }
    }

    public static void isEquals(Object target,Object source,String errorKey){
        isEquals(target,source,errorKey,"");
    }

    public static void isEquals(Object target,Object source,String errorKey,String... errorValues){
        if (!target.equals(source)){
            throw new BusinessException(errorKey,errorValues);
        }
    }


}
