package com.demo.java.dao.mapper;

public final class CustomPageSqlMapper {

    public static final String TABLE_NAME = " CMS_CUSTOM_PAGE ";

    public static final String GET_LIST = "select * from " + TABLE_NAME + " where status = 0 order by id desc";

    public static final String GET = "select * from " + TABLE_NAME + " where id = ?";

    public static final String DELETE = "update " + TABLE_NAME + " set status = ? where id = ?";

}
