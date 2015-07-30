package com.demo.java.menu.service;

import java.util.List;

import com.demo.java.menu.entity.Menu;
import com.demo.java.menu.utils.MenuNode;

public interface MenuService {

    public MenuNode menuTree(int status);

    public List<Menu> getMenuByParentCode(String code);

    public int maxLevel();
}