package com.demo.java.dao.mapper;

public final class ModuleSqlMapper {

    public static final String TABLE_NAME = " CMS_MODULE ";

    public static final String GET_LIST = "select * from " + TABLE_NAME + " where status = 0";

    public static final String GET = "select * from " + TABLE_NAME + " where id = ?";

    public static final String DELETE = "update " + TABLE_NAME + " set status = ? where id = ?";

}