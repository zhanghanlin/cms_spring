package com.demo.java.menu.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.demo.java.common.dao.AbstractDao;
import com.demo.java.common.dict.Status;
import com.demo.java.menu.dao.mapper.MenuSqlMapper;
import com.demo.java.menu.entity.Menu;

@Repository
public class MenuDao extends AbstractDao<Menu> {

    public List<Menu> list(int status) {
        List<Menu> list = jdbcTemplate.query(MenuSqlMapper.GET_LIST, new Object[] { status }, ParameterizedBeanPropertyRowMapper.newInstance(Menu.class));
        if ((list != null) && !list.isEmpty()) {
            return list;
        }
        return null;
    }

    public List<Menu> list() {
        List<Menu> list = jdbcTemplate.query(MenuSqlMapper.GET_ALL, ParameterizedBeanPropertyRowMapper.newInstance(Menu.class));
        if ((list != null) && !list.isEmpty()) {
            return list;
        }
        return null;
    }

    public Menu get(String code) {
        List<Menu> list = jdbcTemplate.query(MenuSqlMapper.GET_BY_CODE, new Object[] { code, Status.NORMAL }, ParameterizedBeanPropertyRowMapper.newInstance(Menu.class));
        if ((list != null) && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public List<Menu> getByParentCode(String code) {
        List<Menu> list = jdbcTemplate.query(MenuSqlMapper.GET_BY_PARENT_CODE, new Object[] { code, Status.NORMAL }, ParameterizedBeanPropertyRowMapper.newInstance(Menu.class));
        if ((list != null) && !list.isEmpty()) {
            return list;
        }
        return null;
    }

    public int maxLevel() {
        return jdbcTemplate.queryForObject(MenuSqlMapper.GET_MAX_LEVEL, new Object[] { Status.NORMAL }, Integer.class);
    }
}
