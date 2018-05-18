package com.tofba.common.exception;

/**
 * 通用组件异常-基类
 * 
 * @author Jeff(fba01)
 * @version [版本号, 2017年8月22日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 5026463863450374456L;    
    private String errorCode;
    
    /**
     * 构造方法。
     * 
     * @param message 异常信息
     */
    public BaseException() {
        super();
    }
    
    /**
     * 构造方法。
     * 
     * @param message 异常信息
     */
    public BaseException(String message) {
        super(message);
    }
    
    /**
     * 构造方法
     * 
     * @param code 错误码
     * @param message 错误提示信息
     */
    public BaseException(String code, String message) {
        super(message);
        this.errorCode = code;
    }
    
    /**
     * 构造方法。
     * 
     * @param cause 异常原因
     */
    public BaseException(Throwable cause) {
        super(cause);
    }
    
    /**
     * 构造方法
     * 
     * @param code 错误码
     * @param message 错误提示信息
     * @param cause 异常原因
     */
    public BaseException(String code, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = code;
    }
    
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * 
     * @Title: getErrorCode @Description: TODO(这里用一句话描述这个方法的作用) @param @return 设定文件 @return String 返回类型 @throws
     */
    public String getErrorCode() {
        return errorCode;
    }
    
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}