package com.demo.java.user.dao.mapper;

public final class UserSqlMapper {

    public static final String TABLE_NAME = " CMS_USER ";

    public static final String GET_BY_UNAME = "select * from " + TABLE_NAME + " where user_name = ?";

    public static final String GET_BY_EMAIL = "select * from " + TABLE_NAME + " where email = ?";

    public static final String GET_BY_PHONE = "select * from " + TABLE_NAME + " where phone = ?";

    public static final String FIND_LIST_BY_PAGE = "SELECT u.*, GROUP_CONCAT(r.`name` SEPARATOR '|') as roles FROM " + TABLE_NAME
            + " u, cms_role r, cms_user_role ur WHERE u.id = ur.user_id AND r.id = ur.role_id GROUP BY u.id ORDER BY u.id DESC LIMIT ?,?";

    public static final String GET_TOTAL_COUNT = "select count(id) from " + TABLE_NAME;
}