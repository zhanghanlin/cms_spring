package com.demo.java.menu.dao.mapper;

public final class MenuSqlMapper {

    public static final String TABLE_NAME = " CMS_MENU ";

    public static final String FIND_LIST = "select * from " + TABLE_NAME + " where status = ? order by LENGTH(code) , weight desc";

    public static final String FIND_ALL = "select * from " + TABLE_NAME + " where status <> 2 order by LENGTH(code) , weight desc";

    public static final String FIND_BY_CODE = "select * from " + TABLE_NAME + " where code = ? and status = ?";

    public static final String FIND_BY_PARENT_ID = "select * from " + TABLE_NAME + " where parent_id = ? and status = ? order by weight desc";

    public static final String FIND_MAX_LEVEL = "select MAX(LENGTH(code)/3) from " + TABLE_NAME + " where status = ?";

    public static final String FIND_MAX_CODE_BY_PARENT_ID = "select MAX(code) from " + TABLE_NAME + " where parent_id = ?";

    public static final String FIND_BY_ROLE = "select m.* from " + TABLE_NAME
            + " m, CMS_ROLE_MENU rm where m.id = rm.menu_id and rm.role_id = ? and m.status = ? order by LENGTH(m.code) , m.weight desc";
}