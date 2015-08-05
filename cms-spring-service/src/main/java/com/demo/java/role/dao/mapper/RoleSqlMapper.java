package com.demo.java.role.dao.mapper;

public final class RoleSqlMapper {

    public static final String TABLE_NAME = " CMS_ROLE ";

    public static final String GET_PAGE_LIST = "select * from " + TABLE_NAME + " ORDER BY id desc LIMIT ?,?";

    public static final String GET_ALL_SIZE = "select count(id) from " + TABLE_NAME;
}