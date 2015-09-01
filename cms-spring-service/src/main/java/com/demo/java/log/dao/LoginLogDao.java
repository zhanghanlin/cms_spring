package com.demo.java.log.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.demo.java.common.dao.AbstractDao;
import com.demo.java.log.dao.mapper.LoginLogSqlMapper;
import com.demo.java.log.entity.LoginLog;

@Repository
public class LoginLogDao extends AbstractDao<LoginLog> {

    public List<LoginLog> findListByPage(int start, int pageSize) {
        return jdbcTemplate.query(LoginLogSqlMapper.FIND_LIST_BY_PAGE, new Object[] { start, pageSize }, ParameterizedBeanPropertyRowMapper.newInstance(LoginLog.class));
    }

    public int getToalCount() {
        return jdbcTemplate.queryForObject(LoginLogSqlMapper.GET_TOTAL_COUNT, Integer.class);
    }
}