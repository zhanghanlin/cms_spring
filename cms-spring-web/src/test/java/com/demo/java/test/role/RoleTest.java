package com.demo.java.test.role;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.java.menu.entity.Menu;
import com.demo.java.menu.service.MenuService;
import com.demo.java.role.entity.Role;
import com.demo.java.role.service.RoleMenuService;
import com.demo.java.role.service.RoleService;
import com.demo.java.test.AbstractTest;

public class RoleTest extends AbstractTest {

    final static Logger LOG = LoggerFactory.getLogger(RoleTest.class);

    @Resource
    RoleService roleService;

    @Resource
    MenuService menuService;

    @Resource
    RoleMenuService roleMenuService;

    @Test
    public void testAdd() {
        Long id = 4L;
        Long[] menuIds = { 4L, 5L, 6L, 9L, 10L };
        Role role = roleService.get(id);
        List<Menu> menuList = menuService.findByIds(menuIds);
        roleMenuService.updateMenu2Role(role.getId(), menuList);
    }
}