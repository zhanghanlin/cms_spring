package com.demo.java.menu.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.demo.java.common.dao.AbstractDao;
import com.demo.java.menu.dao.mapper.MenuSqlMapper;
import com.demo.java.menu.entity.Menu;

@Repository
public class MenuDao extends AbstractDao<Menu> {

    final static Logger LOG = LoggerFactory.getLogger(MenuDao.class);

    public List<Menu> findByParam(int status) {
        return jdbcTemplate.query(MenuSqlMapper.FIND_LIST, new Object[] { status }, ParameterizedBeanPropertyRowMapper.newInstance(Menu.class));
    }

    public List<Menu> findAll() {
        return jdbcTemplate.query(MenuSqlMapper.FIND_ALL, ParameterizedBeanPropertyRowMapper.newInstance(Menu.class));
    }

    public String findMaxCodeByParentId(Long parentId) {
        return jdbcTemplate.queryForObject(MenuSqlMapper.FIND_MAX_CODE_BY_PARENT_ID, new Object[] { parentId }, String.class);
    }

    public List<Menu> findByIds(Long[] menuIds) {
        String sql = "select * from  " + MenuSqlMapper.TABLE_NAME + "  where id in (0";
        for (Long mid : menuIds) {
            sql += "," + mid.toString();
        }
        sql += ") and status = 0 order by LENGTH(code)";
        if (LOG.isDebugEnabled()) {
            LOG.debug("findByIds sql : {}", sql);
        }
        return jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(Menu.class));
    }

    public List<Menu> findByRoleId(Long roleId) {
        return jdbcTemplate.query(MenuSqlMapper.FIND_BY_ROLE, new Object[] { roleId }, ParameterizedBeanPropertyRowMapper.newInstance(Menu.class));
    }

    public List<Menu> findByUserId(Long userId) {
        return jdbcTemplate.query(MenuSqlMapper.FIND_BY_USER, new Object[] { userId }, ParameterizedBeanPropertyRowMapper.newInstance(Menu.class));
    }

    public List<Menu> findByUserId(Long userId, int type) {
        return jdbcTemplate.query(MenuSqlMapper.FIND_BY_USER_AND_TYPE, new Object[] { userId, type }, ParameterizedBeanPropertyRowMapper.newInstance(Menu.class));
    }

}