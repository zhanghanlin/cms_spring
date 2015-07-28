package com.demo.java.menu.dao.mapper;

public final class MenuSqlMapper {

    public static final String TABLE_NAME = " CMS_MENU ";

    public static final String GET_LIST = "select * from " + TABLE_NAME + " order by code";

    public static final String GET_BY_CODE = "select * from " + TABLE_NAME + " where code = ?";
}