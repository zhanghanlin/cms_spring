package com.demo.java.user.dao.mapper;

public final class UserRoleSqlMapper {

    public static final String TABLE_NAME = " CMS_USER_ROLE ";

    public static final String DELETE_BY_ROLE = "delete from " + TABLE_NAME + " where user_id = ?";;
}