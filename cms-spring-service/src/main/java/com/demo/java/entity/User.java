package com.demo.java.entity;

import com.demo.java.annotation.Table;

@Table(name = "CMS_USER")
public class User extends AbstractEntity {

    private static final long serialVersionUID = 7001126580409026146L;

    private String userName;

    private String password;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
