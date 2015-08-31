package com.demo.java.test.menu;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.java.menu.dao.MenuDao;
import com.demo.java.menu.entity.Menu;
import com.demo.java.menu.service.MenuService;
import com.demo.java.test.AbstractTest;

public class MenuTest extends AbstractTest {

    final static Logger LOG = LoggerFactory.getLogger(MenuTest.class);

    @Resource
    MenuDao menuDao;
    @Resource
    MenuService menuService;

    @Test
    public void testTree() {
    }

    @Test
    public void testGet() {
        Menu m = menuDao.get(4L);
        LOG.info(m.toJSON());
    }
}
