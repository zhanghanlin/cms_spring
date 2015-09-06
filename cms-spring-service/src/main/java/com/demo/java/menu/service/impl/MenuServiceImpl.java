package com.demo.java.menu.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.java.common.dict.MenuType;
import com.demo.java.common.dict.Status;
import com.demo.java.common.utils.UserUtils;
import com.demo.java.menu.dao.MenuDao;
import com.demo.java.menu.entity.Menu;
import com.demo.java.menu.service.MenuService;
import com.demo.java.utils.string.StringUtils;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    MenuDao menuDao;

    @Override
    public int add(Menu menu) {
        menu.setCreatedAt(new Date());
        menu.setCreatedBy(UserUtils.getUserName());
        String maxCode = menuDao.findMaxCodeByParentId(menu.getParentId());
        Menu parentMenu = menuDao.get(menu.getParentId());
        String pCode = parentMenu != null ? parentMenu.getCode() : "";
        menu.setCode(nextCode(pCode, maxCode));
        menu.setStatus(Status.NORMAL);
        return menuDao.insert(menu);
    }

    static String nextCode(String parentCode, String maxCode) {
        if (StringUtils.isBlank(maxCode)) {
            return parentCode + "001";
        }
        String code = maxCode.substring(maxCode.length() - 3);
        Integer intCode = Integer.valueOf(code) + 1;
        code = parentCode + StringUtils.leftPad(intCode.toString(), 3, "0");
        return code;
    }

    @Override
    public int updateStatus(Long id, int status) {
        Menu menu = new Menu();
        menu.setId(id);
        menu.setStatus(status);
        menu.setChangedAt(new Date());
        menu.setChangedBy(UserUtils.getUserName());
        return menuDao.update(menu);
    }

    @Override
    public int update(Menu menu) {
        menu.setChangedAt(new Date());
        menu.setChangedBy(UserUtils.getUserName());
        return menuDao.update(menu);
    }

    @Override
    public Menu get(Long id) {
        return menuDao.get(id);
    }

    @Override
    public List<Menu> findByParam(int status) {
        return menuDao.findByParam(status);
    }

    @Override
    public List<Menu> findByIds(Long[] menuIds) {
        return menuDao.findByIds(menuIds);
    }

    @Override
    public List<Menu> findByRoleId(Long roleId) {
        return menuDao.findByRoleId(roleId);
    }

    @Override
    public List<Menu> findByUserId(Long userId) {
        return menuDao.findByUserId(userId);
    }

    @Override
    public List<Menu> findAll() {
        return menuDao.findAll();
    }

    @Override
    public List<Menu> findByUserMenu(Long userId) {
        return menuDao.findByUserId(userId, MenuType.MENU);
    }
}