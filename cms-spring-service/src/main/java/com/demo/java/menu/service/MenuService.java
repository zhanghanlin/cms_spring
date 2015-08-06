package com.demo.java.menu.service;

import java.util.List;

import com.demo.java.menu.entity.Menu;
import com.demo.java.user.entity.User;

public interface MenuService {

    public List<Menu> getMenuByParentCode(String code);

    public int maxLevel();

    public int add(Menu menu, User u);

    public List<String> getTreesNameByCode(String code);

    public int updateStatus(Long id, int status, User u);

    public int update(Menu menu, User u);

    public Menu get(Long id);

    public List<Menu> list(int status);
}