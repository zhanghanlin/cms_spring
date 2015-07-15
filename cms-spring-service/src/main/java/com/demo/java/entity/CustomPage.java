package com.demo.java.entity;

import java.util.Date;

import com.demo.java.annotation.Ignore;
import com.demo.java.annotation.Table;

@Table(name = "CMS_CUSTOM_PAGE")
public class CustomPage extends AbstractEntity {

    private static final long serialVersionUID = 5937921798538621360L;

    private String name;

    private String fileName;

    private String path;

    private int status;

    private int isRefresh;

    private Date refrestTime;

    public String data;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
