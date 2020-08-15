package com.tofba.blog.model.dto;

import lombok.Data;

/**
 * 返回JSON
 * 
 * @author Henry(fba02)
 * @version [版本号, 2020年7月24日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Data
public class JsonResult {
    
    /**
     * 返回的状态码，0：失败，1：成功
     */
    private Integer code;
    
    /**
     * 返回信息
     */
    private String msg;
    
    /**
     * 返回的数据
     */
    private Object result;
    
    /**
     * 只返回状态码
     *
     * @param code 状态码
     */
    public JsonResult(Integer code) {
        this.code = code;
    }
    
    /**
     * 不返回数据的构造方法
     *
     * @param code 状态码
     * @param msg 信息
     */
    public JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    /**
     * 返回数据的构造方法
     *
     * @param code 状态码
     * @param msg 信息
     * @param result 数据
     */
    public JsonResult(Integer code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }
    
    /**
     * 返回状态码和数据
     *
     * @param code 状态码
     * @param result 数据
     */
    public JsonResult(Integer code, Object result) {
        this.code = code;
        this.result = result;
    }
}