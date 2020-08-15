package com.tofba.blog.model.enums;

/**
 * 请求响应enum
 * 
 * @author  Henry(fba02)
 * @version  [版本号, 2020年8月15日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum ResponseStatusEnum {

    /**
     * 请求成功
     */
    SUCCESS(200, "OK"),

    /**
     * 资源为空
     */
    EMPTY(204, "No Content"),

    /**
     * 服务器内部错误
     */
    ERROR(500, "Internal Server Error"),

    /**
     * 未找到资源
     */
    NOTFOUND(404, "Not Found");

    private Integer code;
    private String msg;

    ResponseStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
