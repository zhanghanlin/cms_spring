package com.demo.java.menu.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.java.common.dict.Status;
import com.demo.java.menu.dao.MenuDao;
import com.demo.java.menu.entity.Menu;
import com.demo.java.menu.service.MenuService;
import com.demo.java.menu.utils.MenuNode;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    MenuDao menuDao;

    @Override
    public MenuNode menuTree(int status) {
        List<Menu> list = new ArrayList<Menu>();
        if (Status.ALL == status) {
            list = menuDao.list();
        } else {
            list = menuDao.list(status);
        }
        MenuNode tree = new MenuNode();
        for (Menu m : list) {
            MenuNode t = new MenuNode(m);
            tree.addChildNode(t);
        }
        return tree;
    }
}
