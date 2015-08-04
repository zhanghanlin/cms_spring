package com.demo.java.web.common.vo;

import java.io.Serializable;
import java.util.List;

public class PageVo<T> implements Serializable {

    private static final long serialVersionUID = -3018527518728936040L;

    private int curPage; // 当前页码

    private int pageSize;// 每页数据量

    private int totalPage;// 总页数

    private int totalResults; // 总数据量

    private List<T> result;

    public PageVo(int curPage, int pageSize, int totalResults, List<T> result) {
        super();
        this.curPage = curPage;
        this.pageSize = pageSize;
        this.totalResults = totalResults;
        this.result = result;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        if (totalResults > 0) {
            totalPage = totalResults / pageSize;
        }
        return totalPage;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}