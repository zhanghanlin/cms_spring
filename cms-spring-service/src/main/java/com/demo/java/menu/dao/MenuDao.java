package com.demo.java.menu.dao;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.demo.java.common.dao.AbstractDao;
import com.demo.java.common.dict.Status;
import com.demo.java.menu.dao.mapper.MenuSqlMapper;
import com.demo.java.menu.entity.Menu;

@Repository
public class MenuDao extends AbstractDao<Menu> {

    final static Logger LOG = LoggerFactory.getLogger(MenuDao.class);

    public List<Menu> list(int status) {
        List<Menu> list = jdbcTemplate.query(MenuSqlMapper.FIND_LIST, new Object[] { status }, ParameterizedBeanPropertyRowMapper.newInstance(Menu.class));
        if ((list != null) && !list.isEmpty()) {
            return list;
        }
        return null;
    }

    public List<Menu> list() {
        List<Menu> list = jdbcTemplate.query(MenuSqlMapper.FIND_ALL, ParameterizedBeanPropertyRowMapper.newInstance(Menu.class));
        if ((list != null) && !list.isEmpty()) {
            return list;
        }
        return null;
    }

    public Menu get(String code) {
        List<Menu> list = jdbcTemplate.query(MenuSqlMapper.FIND_BY_CODE, new Object[] { code, Status.NORMAL }, ParameterizedBeanPropertyRowMapper.newInstance(Menu.class));
        if ((list != null) && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public List<Menu> findByParentId(Long parentId) {
        List<Menu> list = jdbcTemplate.query(MenuSqlMapper.FIND_BY_PARENT_ID, new Object[] { parentId, Status.NORMAL }, ParameterizedBeanPropertyRowMapper.newInstance(Menu.class));
        if ((list != null) && !list.isEmpty()) {
            return list;
        }
        return null;
    }

    public String findMaxCodeByParentId(Long parentId) {
        return jdbcTemplate.queryForObject(MenuSqlMapper.FIND_MAX_CODE_BY_PARENT_ID, new Object[] { parentId }, String.class);
    }

    public int maxLevel() {
        return jdbcTemplate.queryForObject(MenuSqlMapper.FIND_MAX_LEVEL, new Object[] { Status.NORMAL }, Integer.class);
    }

    public List<Map<String, Object>> findTreeNameByCode(List<String> param) {
        String sql = "select name from  " + MenuSqlMapper.TABLE_NAME + "  where code in (0";
        for (String c : param) {
            sql += "," + c;
        }
        sql += ") and status = ? order by LENGTH(code)";
        if (LOG.isDebugEnabled()) {
            LOG.debug("getTreesNameByCode sql : {}", sql);
        }
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[] { Status.NORMAL });
        return list;
    }

    public List<Menu> findByIds(Long[] menuIds) {
        String sql = "select * from  " + MenuSqlMapper.TABLE_NAME + "  where id in (0";
        for (Long mid : menuIds) {
            sql += "," + mid.toString();
        }
        sql += ") and status = ? order by LENGTH(code)";
        if (LOG.isDebugEnabled()) {
            LOG.debug("findByIds sql : {}", sql);
        }
        return jdbcTemplate.query(sql, new Object[] { Status.NORMAL }, ParameterizedBeanPropertyRowMapper.newInstance(Menu.class));
    }

    public List<Menu> findByRoleId(Long roleId) {
        return jdbcTemplate.query(MenuSqlMapper.FIND_BY_ROLE, new Object[] { roleId, Status.NORMAL }, ParameterizedBeanPropertyRowMapper.newInstance(Menu.class));
    }
}