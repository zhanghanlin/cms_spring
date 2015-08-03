package com.demo.java.web.controller.vo;

import java.io.Serializable;
import java.util.List;

public class TablePage<T> implements Serializable {

    private static final long serialVersionUID = 2947666872800248033L;

    private int sEcho; // 来自客户端 sEcho 的没有变化的复制品

    private int iTotalRecords; // 实际的行数

    private int iTotalDisplayRecords; // 过滤之后实际的行数。

    private List<T> aaData;

    public TablePage(int sEcho, int iTotalRecords, int iTotalDisplayRecords, List<T> aaData) {
        super();
        this.sEcho = sEcho;
        this.iTotalRecords = iTotalRecords;
        this.iTotalDisplayRecords = iTotalDisplayRecords;
        this.aaData = aaData;
    }

    public int getsEcho() {
        return sEcho;
    }

    public void setsEcho(int sEcho) {
        this.sEcho = sEcho;
    }

    public int getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public List<T> getAaData() {
        return aaData;
    }

    public void setAaData(List<T> aaData) {
        this.aaData = aaData;
    }
}