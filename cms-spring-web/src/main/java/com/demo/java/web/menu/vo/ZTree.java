package com.demo.java.web.menu.vo;

import java.io.Serializable;

import com.demo.java.menu.entity.Menu;

public class ZTree implements Serializable {

    private static final long serialVersionUID = -5724099331231471466L;

    private String id;

    private String pid;

    private String name;

    public ZTree(Menu menu) {
        this.id = menu.getCode();
        this.pid = menu.getParentCode();
        this.name = menu.getName();
    }

    public ZTree(String id, String pid, String name) {
        super();
        this.id = id;
        this.pid = pid;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
