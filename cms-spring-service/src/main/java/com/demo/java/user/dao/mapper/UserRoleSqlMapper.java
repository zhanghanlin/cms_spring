package com.demo.java.user.dao.mapper;

public final class UserRoleSqlMapper {

    public static final String TABLE_NAME = " CMS_USER_ROLE ";

    public static final String DELETE_BY_USER = "delete from " + TABLE_NAME + " where user_id = ?";

    public static final String FIND_BY_USER = "select * from " + TABLE_NAME + " where user_id = ?";
}