package com.tofba.common.exception;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tofba.common.util.StrUtil;

/**
 * 异常工具类
 * 
 * @author Henry(fba02)
 * @version [版本号, 2017年8月24日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ExceptionUtil {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);
    
    /**
     * 获得完整消息，包括异常名
     * 
     * @param e 异常
     * @return 完整消息
     */
    public static String getMessage(Throwable e) {
        logger.error("{}", e.getClass().getSimpleName(), e.getMessage());
        return StrUtil.format("{}: {}", e.getClass().getSimpleName(), e.getMessage());
    }
    
    /**
     * 剥离反射引发的InvocationTargetException、UndeclaredThrowableException中间异常，返回业务本身的异常
     * 
     * @param wrapped 包装的异常
     * @return 剥离后的异常
     */
    public static Throwable unwrap(Throwable wrapped) {
        Throwable unwrapped = wrapped;
        while (true) {
            if (unwrapped instanceof InvocationTargetException) {
                unwrapped = ((InvocationTargetException)unwrapped).getTargetException();
            } else if (unwrapped instanceof UndeclaredThrowableException) {
                unwrapped = ((UndeclaredThrowableException)unwrapped).getUndeclaredThrowable();
            } else {
                return unwrapped;
            }
        }
    }
}