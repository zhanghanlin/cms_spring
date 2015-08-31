package com.demo.java.user.entity;


public class UserVo extends User {

    private static final long serialVersionUID = 1702114573772028667L;

    private String roles;

    public String[] getRoles() {
        return roles.split("\\|");
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
