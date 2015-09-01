package com.demo.java.log.dao.mapper;

public class LoginLogSqlMapper {

    public static final String TABLE_NAME = " CMS_LOGIN_LOG ";

    public static final String FIND_LIST_BY_PAGE = "select * from " + TABLE_NAME + " order by id desc LIMIT ?,?";

    public static final String GET_TOTAL_COUNT = "select count(id) from " + TABLE_NAME;
}
