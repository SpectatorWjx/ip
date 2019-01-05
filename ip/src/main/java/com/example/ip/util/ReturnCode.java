package com.example.ip.util;

import java.io.Serializable;

public class ReturnCode implements Serializable{
    public static final ReturnCode SUCCESS = new ReturnCode(Integer.valueOf(200), "成功");
    public static final ReturnCode AUTH = new ReturnCode(Integer.valueOf(4051), "用户未授权");
    public static final ReturnCode ERROR_FRONT_MESSAGE = new ReturnCode(Integer.valueOf(550), "%s");
    public static final ReturnCode ERR_PARAM = new ReturnCode(Integer.valueOf(4052), "参数错误:%s");
    public static final ReturnCode ERR_TIMEOUT = new ReturnCode(Integer.valueOf(4055), "操作超时");
    public static final ReturnCode ERROR = new ReturnCode(Integer.valueOf(500), "服务器错误:%s");
    public static final ReturnCode ERR_BIZ = new ReturnCode(Integer.valueOf(5051), "业务失败:%s");
    public static final ReturnCode ERR_DB_ERROR = new ReturnCode(Integer.valueOf(5053), "数据库调用失败:%s");
    public static final ReturnCode ERR_RESOURCE_NOT_EXISTS = new ReturnCode(Integer.valueOf(6051), "资源未找到:%s");
    public static final ReturnCode ERR_RESOURCE_NOT_ENOUGH = new ReturnCode(Integer.valueOf(6052), "资源不足:%s");
    public static final ReturnCode ERR_RESOURCE_ILLEGAL = new ReturnCode(Integer.valueOf(6053), "资源非法:%s");
    public static final ReturnCode ERR_FIELD_NOT_ENOUGH = new ReturnCode(Integer.valueOf(6054), "属性不存在%s");
    public static final ReturnCode ERROR_CUSTOM_FAIL = new ReturnCode(Integer.valueOf(7001),"%s");
    private Integer status;
    private String message;

    protected ReturnCode(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }
}
