package com.demo.java.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.java.user.dao.MenuDao;
import com.demo.java.user.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    MenuDao menuDao;
}
