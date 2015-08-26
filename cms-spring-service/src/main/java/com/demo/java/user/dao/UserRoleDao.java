package com.demo.java.user.dao;

import org.springframework.stereotype.Repository;

import com.demo.java.common.dao.AbstractDao;
import com.demo.java.user.dao.mapper.UserRoleSqlMapper;
import com.demo.java.user.entity.UserRole;

@Repository
public class UserRoleDao extends AbstractDao<UserRole> {

    public void deleteByUserId(Long userId) {
        jdbcTemplate.update(UserRoleSqlMapper.DELETE_BY_ROLE, new Object[] { userId });
    }
}