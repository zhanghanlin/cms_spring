package com.demo.java.menu.service;

import java.util.List;

import com.demo.java.menu.entity.Menu;

public interface MenuService {

    public List<Menu> getMenuByParentCode(String code);

    public int maxLevel();

    public int add(Menu menu);

    public List<String> getTreesNameByCode(String code);

    public int updateStatus(Long id, int status);

    public int update(Menu menu);

    public Menu get(Long id);

    public List<Menu> list(int status);
}