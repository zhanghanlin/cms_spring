package com.demo.java.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.demo.java.dao.mapper.UserSqlMapper;
import com.demo.java.entity.User;

@Repository
public class UserDao extends AbstractDao<User> {

    public User getByUserName(String userName) {
        List<User> list = jdbcTemplate.query(UserSqlMapper.GET_BY_UNAME, new Object[] { userName }, ParameterizedBeanPropertyRowMapper.newInstance(User.class));
        if ((list != null) && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
