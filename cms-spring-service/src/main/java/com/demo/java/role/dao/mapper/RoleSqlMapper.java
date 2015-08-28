package com.demo.java.role.dao.mapper;

public final class RoleSqlMapper {

    public static final String TABLE_NAME = " CMS_ROLE ";

    public static final String FIND_LIST = "select * from " + TABLE_NAME + " where status = ? order by id";

    public static final String FIND_ALL = "select * from " + TABLE_NAME + " where status <> ? order by id";

    public static final String FIND_LIST_PAGE = "select * from " + TABLE_NAME + " where status <> 2 ORDER BY id desc LIMIT ?,?";

    public static final String FIND_NORMAL_TOTAL_COUNT = "select count(id) from " + TABLE_NAME + " where status <> 2 ";

    public static final String FIND_BY_USER = "select cr.* from " + TABLE_NAME + " cr,cms_user_role ur where cr.status = 0 and cr.id = ur.role_id and ur.user_id = ?";
}