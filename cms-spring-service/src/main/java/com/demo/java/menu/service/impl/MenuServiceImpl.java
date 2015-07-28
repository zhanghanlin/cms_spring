package com.demo.java.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.java.menu.dao.MenuDao;
import com.demo.java.menu.entity.Menu;
import com.demo.java.menu.entity.MenuNode;
import com.demo.java.menu.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    MenuDao menuDao;

    @Override
    public List<MenuNode> list() {
        List<Menu> list = menuDao.list();
        MenuNode tree = new MenuNode();
        for (Menu m : list) {
            MenuNode t = new MenuNode(m);
            tree.addChildNode(t);
        }
        return tree.getChildNode();
    }
}
