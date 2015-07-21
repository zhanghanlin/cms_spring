package com.demo.java.web.response;

public enum DefaultResponseEnum implements ResponseEnum {

    SUCCESS("200", "成功"), ERROR("500", "失败");

    private String code;
    private String msg;

    private DefaultResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}