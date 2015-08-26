package com.demo.java.role.service;

import java.util.List;

import com.demo.java.menu.entity.Menu;
import com.demo.java.role.entity.RoleMenu;

public interface RoleMenuService {
    /**
     * 更新权限到角色.<br/>
     * 
     * @author zhanghanlin
     * @param id
     * @param menuIdList
     * @return
     * @since JDK 1.7
     */
    int updateMenu2Role(Long id, List<Menu> menus);

    /**
     * 根据角色Id查询菜单集合.<br/>
     * 
     * @author zhanghanlin
     * @param roleId
     * @return
     * @since JDK 1.7
     */
    List<RoleMenu> findByRoleId(Long roleId);
}
