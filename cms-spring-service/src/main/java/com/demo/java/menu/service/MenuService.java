package com.demo.java.menu.service;

import java.util.List;

import com.demo.java.menu.entity.Menu;
import com.demo.java.menu.utils.MenuNode;
import com.demo.java.user.entity.User;

public interface MenuService {

    public MenuNode menuTree(int status);

    public List<Menu> getMenuByParentCode(String code);

    public int maxLevel();

    public int add(Menu menu, User u);
}