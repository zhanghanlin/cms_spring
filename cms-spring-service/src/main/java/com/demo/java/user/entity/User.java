package com.demo.java.user.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.demo.java.annotation.Table;
import com.demo.java.common.entity.AbstractEntity;

@Table(name = "CMS_USER")
public class User extends AbstractEntity {

    private static final long serialVersionUID = 2531665438498086670L;

    private String userName;

    @JSONField(serialize = false)
    private String password;

    private String email;

    private String phone;

    private int status;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
