package com.demo.java.user.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.demo.java.common.dao.AbstractDao;
import com.demo.java.user.dao.mapper.UserSqlMapper;
import com.demo.java.user.entity.User;

@Repository
public class UserDao extends AbstractDao<User> {

    public User getByUserName(String userName) {
        List<User> list = jdbcTemplate.query(UserSqlMapper.GET_BY_UNAME, new Object[] { userName }, ParameterizedBeanPropertyRowMapper.newInstance(User.class));
        if ((list != null) && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public User getByEmail(String email) {
        List<User> list = jdbcTemplate.query(UserSqlMapper.GET_BY_EMAIL, new Object[] { email }, ParameterizedBeanPropertyRowMapper.newInstance(User.class));
        if ((list != null) && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public User getByPhone(String phone) {
        List<User> list = jdbcTemplate.query(UserSqlMapper.GET_BY_PHONE, new Object[] { phone }, ParameterizedBeanPropertyRowMapper.newInstance(User.class));
        if ((list != null) && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public List<User> pageList(int start, int pageSize) {
        return jdbcTemplate.query(UserSqlMapper.GET_PAGE_LIST, new Object[] { start, pageSize }, ParameterizedBeanPropertyRowMapper.newInstance(User.class));
    }

    public int size() {
        return jdbcTemplate.queryForObject(UserSqlMapper.GET_ALL_SIZE, Integer.class);
    }
}