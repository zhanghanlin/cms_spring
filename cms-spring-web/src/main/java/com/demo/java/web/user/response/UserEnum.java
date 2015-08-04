package com.demo.java.web.user.response;

import com.demo.java.web.common.response.Enum;

public enum UserEnum implements Enum {

    SUCCESS(200, "成功"), //
    ERROR_PARAM_NULL(501, "参数异常"), //
    ERROR_USERNAME_PWD(502, "用户名或密码错误"), //
    ERROR(500, "失败");

    private int code;
    private String msg;

    private UserEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}