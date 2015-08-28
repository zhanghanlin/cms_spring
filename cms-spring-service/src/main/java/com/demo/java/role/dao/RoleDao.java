package com.demo.java.role.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.demo.java.common.dao.AbstractDao;
import com.demo.java.common.dict.Status;
import com.demo.java.menu.dao.mapper.MenuSqlMapper;
import com.demo.java.role.dao.mapper.RoleSqlMapper;
import com.demo.java.role.entity.Role;

@Repository
public class RoleDao extends AbstractDao<Role> {

    final static Logger LOG = LoggerFactory.getLogger(RoleDao.class);

    public List<Role> findListByPage(int start, int pageSize) {
        return jdbcTemplate.query(RoleSqlMapper.FIND_LIST_PAGE, new Object[] { start, pageSize }, ParameterizedBeanPropertyRowMapper.newInstance(Role.class));
    }

    public int findNormalTotalCount() {
        return jdbcTemplate.queryForObject(RoleSqlMapper.FIND_NORMAL_TOTAL_COUNT, Integer.class);
    }

    public List<Role> findByIds(Long[] roleIds) {
        String sql = "select * from  " + MenuSqlMapper.TABLE_NAME + "  where id in (0";
        for (Long rid : roleIds) {
            sql += "," + rid.toString();
        }
        sql += ") and status = ?";
        if (LOG.isDebugEnabled()) {
            LOG.debug("findByIds sql : {}", sql);
        }
        return jdbcTemplate.query(sql, new Object[] { Status.NORMAL }, ParameterizedBeanPropertyRowMapper.newInstance(Role.class));
    }

    public List<Role> findList(int status) {
        String sql = RoleSqlMapper.FIND_LIST;
        int param = status;
        if (status == Status.ALL) {
            sql = RoleSqlMapper.FIND_ALL;
            param = Status.DELETE;
        }
        return jdbcTemplate.query(sql, new Object[] { param }, ParameterizedBeanPropertyRowMapper.newInstance(Role.class));
    }

    public List<Role> findByUserId(Long userId) {
        return jdbcTemplate.query(RoleSqlMapper.FIND_BY_USER, new Object[] { userId }, ParameterizedBeanPropertyRowMapper.newInstance(Role.class));
    }
}