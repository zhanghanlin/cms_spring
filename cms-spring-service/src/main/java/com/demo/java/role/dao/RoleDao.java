package com.demo.java.role.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.demo.java.common.dao.AbstractDao;
import com.demo.java.role.dao.mapper.RoleSqlMapper;
import com.demo.java.role.entity.Role;

@Repository
public class RoleDao extends AbstractDao<Role> {

    public List<Role> pageList(int start, int pageSize) {
        return jdbcTemplate.query(RoleSqlMapper.GET_PAGE_LIST, new Object[] { start, pageSize }, ParameterizedBeanPropertyRowMapper.newInstance(Role.class));
    }

    public int size() {
        return jdbcTemplate.queryForObject(RoleSqlMapper.GET_ALL_SIZE, Integer.class);
    }
}