package com.demo.java.role.dao.mapper;

public final class RoleMenuSqlMapper {

    public static final String TABLE_NAME = " CMS_ROLE_MENU ";

    public static final String DELETE_BY_ROLE = "delete from " + TABLE_NAME + " where role_id = ?";

    public static final String FIND_BY_ROLE = "select * from " + TABLE_NAME + " where role_id = ?";
}