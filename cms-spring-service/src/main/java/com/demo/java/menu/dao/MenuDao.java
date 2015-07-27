package com.demo.java.menu.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.demo.java.common.dao.AbstractDao;
import com.demo.java.menu.dao.mapper.MenuSqlMapper;
import com.demo.java.menu.entity.Menu;

@Repository
public class MenuDao extends AbstractDao<Menu> {

    public List<Menu> list() {
        List<Menu> list = jdbcTemplate.query(MenuSqlMapper.GET_LIST, ParameterizedBeanPropertyRowMapper.newInstance(Menu.class));
        if ((list != null) && !list.isEmpty()) {
            return list;
        }
        return null;
    }

    public Menu get(String code) {
        List<Menu> list = jdbcTemplate.query(MenuSqlMapper.GET_BY_CODE, new Object[] { code }, ParameterizedBeanPropertyRowMapper.newInstance(Menu.class));
        if ((list != null) && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
