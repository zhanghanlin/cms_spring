package com.demo.java.common.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

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
    protected JdbcTemplate jdbcTemplate;

    public int insert(T t) {
        String sql = ReflectUtils.getInsertSQL(t);
        Object[] objs = ReflectUtils.getBeanValue(t).toArray();
        return jdbcTemplate.update(sql, objs);
    }

    public int update(T t) {
        String sql = ReflectUtils.getUpdateSQL(t);
        Object[] objs = ReflectUtils.getBeanValue(t).toArray();
        return jdbcTemplate.update(sql, objs);
    }

    @SuppressWarnings("unchecked")
    public T get(Long id) {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Class<T> entityClass = (Class<T>) params[0];
        String sql = "SELECT * FROM " + ReflectUtils.class2Table(entityClass) + " WHERE ID = ?";
        List<T> list = jdbcTemplate.query(sql, new Object[] { id }, ParameterizedBeanPropertyRowMapper.newInstance(entityClass));
        if ((list != null) && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}