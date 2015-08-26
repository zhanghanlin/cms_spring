package com.demo.java.role.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.java.role.dao.RoleDao;
import com.demo.java.role.dao.RoleMenuDao;
import com.demo.java.role.entity.Role;
import com.demo.java.role.service.RoleService;
import com.demo.java.utils.shiro.UserUtils;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleDao roleDao;

    @Resource
    RoleMenuDao roleMenuDao;

    @Override
    public List<Role> pageList(int curPage, int pageSize) {
        return roleDao.pageList((curPage - 1) * pageSize, pageSize);
    }

    @Override
    public int findPageListTotalCount() {
        return roleDao.findPageListTotalCount();
    }

    @Override
    public int add(Role role) {
        role.setCreatedAt(new Date());
        role.setCreatedBy(UserUtils.getUserName());
        return roleDao.save(role);
    }

    @Override
    public Role get(Long id) {
        return roleDao.get(id);
    }

    @Override
    public int update(Role role) {
        role.setChangedAt(new Date());
        role.setCreatedBy(UserUtils.getUserName());
        return roleDao.update(role);
    }

    @Override
    public List<Role> findByIds(Long[] roleIds) {
        return roleDao.findByIds(roleIds);
    }

    @Override
    public List<Role> list(int status) {

        // TODO Auto-generated method stub
        return null;
    }
}