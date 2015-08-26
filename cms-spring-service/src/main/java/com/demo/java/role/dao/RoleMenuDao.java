package com.demo.java.role.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.demo.java.common.dao.AbstractDao;
import com.demo.java.role.dao.mapper.RoleMenuSqlMapper;
import com.demo.java.role.entity.RoleMenu;

@Repository
public class RoleMenuDao extends AbstractDao<RoleMenu> {

    public void deleteByRoleId(Long roleId) {
        jdbcTemplate.update(RoleMenuSqlMapper.DELETE_BY_ROLE, new Object[] { roleId });
    }

    public List<RoleMenu> findByRoleId(Long roleId) {
        return jdbcTemplate.query(RoleMenuSqlMapper.FIND_BY_ROLE, new Object[] { roleId }, ParameterizedBeanPropertyRowMapper.newInstance(RoleMenu.class));
    }
}