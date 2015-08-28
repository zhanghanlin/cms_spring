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
    public List<Role> findListByPage(int curPage, int pageSize) {
        return roleDao.findListByPage((curPage - 1) * pageSize, pageSize);
    }

    @Override
    public int getTotalCount() {
        return roleDao.findNormalTotalCount();
    }

    @Override
    public int add(Role role) {
        role.setCreatedAt(new Date());
        role.setCreatedBy(UserUtils.getUserName());
        return roleDao.insert(role);
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
    public List<Role> findList(int status) {
        return roleDao.findList(status);
    }

    @Override
    public List<Role> findByUserId(Long userId) {
        return roleDao.findByUserId(userId);
    }
}