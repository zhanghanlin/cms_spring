package com.demo.java.dao;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.demo.java.entity.Module;
import com.demo.java.utils.reflect.ReflectUtils;

/**
 * AbstractDao
 * 
 * @author zhanghanlin
 * @version @param <T>
 * @since JDK 1.7
 */
public abstract class AbstractDao<T> {

    static final Logger LOG = LoggerFactory.getLogger(AbstractDao.class);

    @Resource
    JdbcTemplate jdbcTemplate;

    public int save(T t) {
        String sql = ReflectUtils.getInsertSQL(t);
        Object[] objs = ReflectUtils.getBeanValue(t).toArray();
        return jdbcTemplate.update(sql, objs);
    }

    public static void main(String[] args) {
        Module t = new Module();
        String sql = ReflectUtils.getInsertSQL(t);
        System.out.println(sql);
    }

    public int update(T t) {
        String sql = ReflectUtils.getUpdateSQL(t);
        Object[] objs = ReflectUtils.getBeanValue(t).toArray();
        return jdbcTemplate.update(sql, objs);
    }
}