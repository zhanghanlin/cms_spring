package com.demo.java.user.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.demo.java.common.dao.AbstractDao;
import com.demo.java.user.dao.mapper.UserRoleSqlMapper;
import com.demo.java.user.entity.UserRole;

@Repository
public class UserRoleDao extends AbstractDao<UserRole> {

    public void deleteByUserId(Long userId) {
        jdbcTemplate.update(UserRoleSqlMapper.DELETE_BY_USER, new Object[] { userId });
    }

    public List<UserRole> findByUserId(Long userId) {
        return jdbcTemplate.query(UserRoleSqlMapper.FIND_BY_USER, new Object[] { userId }, ParameterizedBeanPropertyRowMapper.newInstance(UserRole.class));
    }
}