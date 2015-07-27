package com.demo.java.test.menu;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.java.menu.dao.MenuDao;
import com.demo.java.menu.entity.Menu;
import com.demo.java.menu.entity.MenuTree;
import com.demo.java.test.AbstractTest;

public class MenuTest extends AbstractTest {

    final static Logger LOG = LoggerFactory.getLogger(MenuTest.class);

    @Resource
    MenuDao menuDao;

    @Test
    public void testTree() {
        String topCode = "0";
        Menu menu = menuDao.get(topCode);
        if (menu == null) {
            LOG.info("top menu null");
        }
        List<Menu> list = menuDao.list();
        MenuTree tree = new MenuTree(menu);
        for (Menu m : list) {
            MenuTree t = new MenuTree(m);
            tree.addChildNode(t);
        }
        LOG.info(tree.toJSON());
    }
}
