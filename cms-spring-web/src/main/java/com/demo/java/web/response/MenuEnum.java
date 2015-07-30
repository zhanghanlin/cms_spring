package com.demo.java.web.response;

public enum MenuEnum implements Enum {

    SUCCESS(200, "成功"), //
    ERROR(500, "失败");

    private int code;
    private String msg;

    private MenuEnum(int code, String msg) {
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