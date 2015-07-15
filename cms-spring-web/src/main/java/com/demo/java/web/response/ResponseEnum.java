package com.demo.java.web.response;

public enum ResponseEnum {

    SUCCESS("200", "成功"), ERROR("500", "失败");

    private String code;
    private String msg;

    private ResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}