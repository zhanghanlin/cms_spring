package com.demo.java.user.dao.mapper;

public final class UserSqlMapper {

    public static final String TABLE_NAME = " CMS_USER ";

    public static final String GET_BY_UNAME = "select * from " + TABLE_NAME + " where user_name = ?";

    public static final String GET_BY_EMAIL = "select * from " + TABLE_NAME + " where email = ?";

    public static final String GET_BY_PHONE = "select * from " + TABLE_NAME + " where phone = ?";

    public static final String GET_PAGE_LIST = "select * from " + TABLE_NAME + " ORDER BY id desc LIMIT ?,?";

    public static final String GET_ALL_SIZE = "select count(id) from " + TABLE_NAME;
}