package com.demo.java.role.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.java.role.dao.RoleDao;
import com.demo.java.role.entity.Role;
import com.demo.java.role.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleDao roleDao;

    @Override
    public List<Role> pageList(int curPage, int pageSize) {
        return roleDao.pageList((curPage - 1) * pageSize, pageSize);
    }

    @Override
    public int size() {
        return roleDao.size();
    }
}