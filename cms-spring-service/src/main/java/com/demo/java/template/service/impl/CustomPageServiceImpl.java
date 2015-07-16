package com.demo.java.template.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.java.template.dao.CustomPageDao;
import com.demo.java.template.entity.CustomPage;
import com.demo.java.template.service.CustomPageService;

@Service("customPageService")
public class CustomPageServiceImpl implements CustomPageService {

    @Resource
    CustomPageDao customPageDao;

    @Override
    public void save(CustomPage t) {
        customPageDao.save(t);
    }

    @Override
    public List<CustomPage> getList() {
        return customPageDao.getList();
    }

    @Override
    public CustomPage get(Long id) {
        return customPageDao.get(id);
    }

    @Override
    public int update(CustomPage t) {
        return customPageDao.update(t);
    }

    @Override
    public int delete(Long id) {
        return customPageDao.delete(id);
    }

}
