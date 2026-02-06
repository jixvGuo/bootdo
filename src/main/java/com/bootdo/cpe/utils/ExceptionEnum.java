package com.bootdo.cpe.utils;

/**
 * 异常枚举
 *
 * @author houzb
 * @version 1.0
 * @date 2022-02-15 23:56
 */
public enum ExceptionEnum {
    AWARD_TASK_ROLE_NO_SEL_AWARD(1000, "未选择奖项进入");
    private int code;
    private String msg;

    ExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
