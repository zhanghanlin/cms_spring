package com.demo.java.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.java.dao.ModuleDao;
import com.demo.java.entity.Module;
import com.demo.java.service.ModuleService;

@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {

    @Resource
    ModuleDao moduleDao;

    @Override
    public void save(Module t) {
        moduleDao.save(t);
    }

    @Override
    public List<Module> getList() {
        return moduleDao.getList();
    }

    @Override
    public Module get(Long id) {
        return moduleDao.get(id);
    }

    @Override
    public int update(Module t) {
        return moduleDao.update(t);
    }

    @Override
    public int delete(Long id) {
        return moduleDao.delete(id);
    }
}
