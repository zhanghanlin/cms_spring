package com.demo.java.template.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.demo.java.common.dao.AbstractDao;
import com.demo.java.common.dict.Status;
import com.demo.java.template.dao.mapper.ModuleSqlMapper;
import com.demo.java.template.entity.Module;

@Repository
public class ModuleDao extends AbstractDao<Module> {

    /**
     * 
     * 根据类型查询数据即可.<br/>
     * 
     * @author zhanghanlin
     * @param type
     * @return
     * @since JDK 1.7
     */
    public List<Module> getList() {
        return jdbcTemplate.query(ModuleSqlMapper.GET_LIST, ParameterizedBeanPropertyRowMapper.newInstance(Module.class));
    }

    public Module get(Long id) {
        List<Module> list = jdbcTemplate.query(ModuleSqlMapper.GET, new Object[] { id }, ParameterizedBeanPropertyRowMapper.newInstance(Module.class));
        if ((list != null) && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public int delete(Long id) {
        return jdbcTemplate.update(ModuleSqlMapper.DELETE, new Object[] { Status.DELETE, id });
    }
}