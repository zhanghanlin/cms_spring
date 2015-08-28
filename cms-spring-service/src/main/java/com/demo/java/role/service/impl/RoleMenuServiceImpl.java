package com.demo.java.role.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.java.menu.entity.Menu;
import com.demo.java.role.dao.RoleMenuDao;
import com.demo.java.role.entity.RoleMenu;
import com.demo.java.role.service.RoleMenuService;
import com.demo.java.utils.shiro.UserUtils;

@Service("roleMenuService")
public class RoleMenuServiceImpl implements RoleMenuService {

    @Resource
    RoleMenuDao roleMenuDao;

    @Override
    public int updateMenu2Role(Long id, List<Menu> menus) {
        int res = 0;
        if ((id != null) && (id.longValue() > 0)) {
            roleMenuDao.deleteByRoleId(id);
        }
        for (Menu m : menus) {
            RoleMenu rm = new RoleMenu();
            rm.setCreatedBy(UserUtils.getUserName());
            rm.setCreatedAt(new Date());
            rm.setRoleId(id);
            rm.setMenuId(m.getId());
            res = roleMenuDao.insert(rm);
            if (res == 0) {
                break;
            }
        }
        return res;
    }

    @Override
    public List<RoleMenu> findByRoleId(Long roleId) {
        return roleMenuDao.findByRoleId(roleId);
    }
}