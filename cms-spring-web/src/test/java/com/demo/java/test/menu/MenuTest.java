package com.demo.java.test.menu;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.java.menu.dao.MenuDao;
import com.demo.java.menu.entity.Menu;
import com.demo.java.menu.utils.MenuNode;
import com.demo.java.test.AbstractTest;

public class MenuTest extends AbstractTest {

    final static Logger LOG = LoggerFactory.getLogger(MenuTest.class);

    @Resource
    MenuDao menuDao;

    @Test
    public void testTree() {
        List<Menu> list = menuDao.list();
        MenuNode tree = new MenuNode();
        for (Menu m : list) {
            MenuNode t = new MenuNode(m);
            tree.addChildNode(t);
        }
        LOG.info(tree.toJSON());
    }

    @Test
    public void testMaxLevel() {
        LOG.info("max level : {}", menuDao.maxLevel());
    }
}
