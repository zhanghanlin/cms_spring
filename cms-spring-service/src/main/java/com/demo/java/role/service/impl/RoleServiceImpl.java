package com.demo.java.role.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.java.role.dao.RoleDao;
import com.demo.java.role.entity.Role;
import com.demo.java.role.service.RoleService;
import com.demo.java.user.entity.User;

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

    @Override
    public int add(Role role, User u) {
        role.setCreatedAt(new Date());
        role.setCreatedBy(u.getUserName());
        return roleDao.save(role);
    }

    @Override
    public Role get(Long id) {
        return roleDao.get(id);
    }

    @Override
    public int update(Role role, User u) {
        role.setChangedAt(new Date());
        role.setChangedBy(u.getUserName());
        return roleDao.update(role);
    }
}