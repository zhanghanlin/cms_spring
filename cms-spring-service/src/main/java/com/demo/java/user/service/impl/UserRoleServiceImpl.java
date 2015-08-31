package com.demo.java.user.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.java.common.utils.UserUtils;
import com.demo.java.role.entity.Role;
import com.demo.java.user.dao.UserRoleDao;
import com.demo.java.user.entity.UserRole;
import com.demo.java.user.service.UserRoleService;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    UserRoleDao userRoleDao;

    @Override
    public int updateRole2User(Long id, List<Role> roleList) {
        int res = 0;
        if ((id != null) && (id.longValue() > 0)) {
            userRoleDao.deleteByUserId(id);
        }
        for (Role r : roleList) {
            UserRole ur = new UserRole();
            ur.setCreatedBy(UserUtils.getUserName());
            ur.setCreatedAt(new Date());
            ur.setRoleId(r.getId());
            ur.setUserId(id);
            res = userRoleDao.insert(ur);
            if (res == 0) {
                break;
            }
        }
        return res;
    }

    @Override
    public List<UserRole> findByUserId(Long userId) {
        return userRoleDao.findByUserId(userId);
    }
}