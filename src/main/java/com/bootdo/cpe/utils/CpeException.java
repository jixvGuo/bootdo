package com.bootdo.cpe.utils;

/**
 * 自定义异常处理
 *
 * @author houzb
 * @version 1.0
 * @date 2022-02-15 23:54
 */
public class CpeException extends Exception {

    private int code;
    private String msg;

    public CpeException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CpeException(ExceptionEnum e) {
        this.code = e.getCode();
        this.msg = e.getMsg();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
