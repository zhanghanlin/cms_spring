package com.demo.java.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.java.menu.dao.MenuDao;
import com.demo.java.menu.entity.Menu;
import com.demo.java.menu.entity.MenuTree;
import com.demo.java.menu.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    MenuDao menuDao;

    @Override
    public MenuTree list() {
        String topCode = "0";
        Menu menu = menuDao.get(topCode);
        if (menu == null) {
            return null;
        }
        List<Menu> list = menuDao.list();
        MenuTree tree = new MenuTree(menu);
        for (Menu m : list) {
            MenuTree t = new MenuTree(m);
            tree.addChildNode(t);
        }
        return tree;
    }
}
