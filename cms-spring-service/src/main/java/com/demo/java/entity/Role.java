package com.demo.java.entity;

import com.demo.java.annotation.Table;

@Table(name = "CMS_ROLE")
public class Role extends AbstractEntity {

    private static final long serialVersionUID = 376155477413085670L;

    private String name;

    private String note;

    private int status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
