package com.demo.java.user.dao.mapper;

public final class UserSqlMapper {

    public static final String TABLE_NAME = " CMS_USER ";

    public static final String GET_BY_UNAME = "select * from " + TABLE_NAME + " where user_name = ?";

}