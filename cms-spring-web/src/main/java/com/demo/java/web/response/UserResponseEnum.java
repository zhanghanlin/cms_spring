package com.demo.java.web.response;

public enum UserResponseEnum implements ResponseEnum {

    SUCCESS("200", "成功"), //
    ERROR_PARAM_NULL("501", "参数异常"), //
    ERROR_USERNAME_PWD("502", "用户名或密码错误"), //
    ERROR("500", "失败");

    private String code;
    private String msg;

    private UserResponseEnum(String code, String msg) {
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