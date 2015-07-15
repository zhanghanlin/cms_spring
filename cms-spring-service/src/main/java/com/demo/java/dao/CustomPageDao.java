package com.demo.java.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.demo.java.dao.mapper.CustomPageSqlMapper;
import com.demo.java.dict.Status;
import com.demo.java.entity.CustomPage;

@Repository
public class CustomPageDao extends AbstractDao<CustomPage> {

    /**
     * 
     * 根据类型查询数据即可.<br/>
     * 
     * @author zhanghanlin
     * @param type
     * @return
     * @since JDK 1.7
     */
    public List<CustomPage> getList() {
        return jdbcTemplate.query(CustomPageSqlMapper.GET_LIST, ParameterizedBeanPropertyRowMapper.newInstance(CustomPage.class));
    }

    public CustomPage get(Long id) {
        List<CustomPage> list = jdbcTemplate.query(CustomPageSqlMapper.GET, new Object[] { id }, ParameterizedBeanPropertyRowMapper.newInstance(CustomPage.class));
        if ((list != null) && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public int delete(Long id) {
        return jdbcTemplate.update(CustomPageSqlMapper.DELETE, new Object[] { Status.DELETE, id });
    }
}