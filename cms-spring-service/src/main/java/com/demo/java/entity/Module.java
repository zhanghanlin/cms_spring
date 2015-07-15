package com.demo.java.entity;

import java.util.Date;

import com.demo.java.annotation.Ignore;
import com.demo.java.annotation.Table;

@Table(name = "CMS_MODULE")
public class Module extends AbstractEntity {
    private static final long serialVersionUID = -7812254668729432881L;

    private String name;

    private String fileName;

    private int type;

    private int status;

    private int isRefresh;

    private Date refrestTime;

    private String data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsRefresh() {
        return isRefresh;
    }

    public void setIsRefresh(int isRefresh) {
        this.isRefresh = isRefresh;
    }

    public Date getRefrestTime() {
        return refrestTime;
    }

    public void setRefrestTime(Date refrestTime) {
        this.refrestTime = refrestTime;
    }

    @Ignore
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
