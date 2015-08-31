package com.demo.java.menu.service;

import java.util.List;

import com.demo.java.menu.entity.Menu;

public interface MenuService {

    /**
     * 新增菜单.<br/>
     * 
     * @author zhanghanlin
     * @param menu
     * @return
     * @since JDK 1.7
     */
    public int add(Menu menu);

    /**
     * 根据Id更新菜单状态.<br/>
     * 
     * @author zhanghanlin
     * @param id
     * @param status
     * @return
     * @since JDK 1.7
     */
    public int updateStatus(Long id, int status);

    /**
     * 更新菜单信息.<br/>
     * 
     * @author zhanghanlin
     * @param menu
     * @return
     * @since JDK 1.7
     */
    public int update(Menu menu);

    /**
     * 根据Id获取菜单信息.<br/>
     * 
     * @author zhanghanlin
     * @param id
     * @return
     * @since JDK 1.7
     */
    public Menu get(Long id);

    /**
     * 根据菜单状态查询菜单列表.<br/>
     * 
     * @author zhanghanlin
     * @param status
     * @return
     * @since JDK 1.7
     */
    public List<Menu> findByParam(int status);

    /**
     * 根据Id数组查询.<br/>
     * 
     * @author zhanghanlin
     * @param menuIds
     * @return
     * @since JDK 1.7
     */
    public List<Menu> findByIds(Long[] menuIds);

    /**
     * 根据角色查询对应权限.<br/>
     * 
     * @author zhanghanlin
     * @param roleId
     * @return
     * @since JDK 1.7
     */
    public List<Menu> findByRoleId(Long roleId);

    /**
     * 根据用户查询对应权限.<br/>
     * 
     * @author zhanghanlin
     * @param userId
     * @return
     * @since JDK 1.7
     */
    public List<Menu> findByUserId(Long userId);

    /**
     * 根据用户查询对应导航树.<br/>
     * 
     * @author zhanghanlin
     * @param userId
     * @return
     * @since JDK 1.7
     */
    public List<Menu> findByUserMenu(Long userId);

    /**
     * 得到所有菜单信息 不包括删除.<br/>
     * 
     * @author zhanghanlin
     * @return
     * @since JDK 1.7
     */
    public List<Menu> findAll();
}