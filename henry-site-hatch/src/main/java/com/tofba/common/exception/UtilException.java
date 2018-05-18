package com.tofba.common.exception;

import com.tofba.common.util.StrUtil;

/**
 * 未初始化异常
 * 
 * @author Henry(fba02)
 * @version [版本号, 2017年8月24日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class UtilException extends RuntimeException {
    private static final long serialVersionUID = 8247610319171014183L;
    
    public UtilException(Throwable e) {
        super(ExceptionUtil.getMessage(e), e);
    }
    
    public UtilException(String message) {
        super(message);
    }
    
    public UtilException(String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params));
    }
    
    public UtilException(String message, Throwable throwable) {
        super(message, throwable);
    }
    
    public UtilException(Throwable throwable, String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params), throwable);
    }
}