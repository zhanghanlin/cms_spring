package com.demo.java.web.role.response;

import com.demo.java.web.common.response.Enum;

public enum RoleEnum implements Enum {

    SUCCESS(200, "成功"), //
    ERROR(500, "失败");

    private int code;
    private String msg;

    private RoleEnum(int code, String msg) {
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