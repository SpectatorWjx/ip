package com.example.ip.util;

import java.io.Serializable;

/***
 @author:WM
 @date:2018-01-25
 @desc://todo
 */
public class Render implements Serializable {
    private static final long serialVersionUID = -2842061383738959817L;
    private Integer status;
    private String message;
    private Object data;

    private Render() {
        status= ReturnCode.SUCCESS.getStatus();
        message=ReturnCode.SUCCESS.getMessage();
    }

    public static Render success(Object data) {
        Render r = new Render();
        r.data=data;
        return r;
    }
    public static Render success() {
        return new Render();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
