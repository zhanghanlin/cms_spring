package com.demo.java.menu.service;

import java.util.List;

import com.demo.java.menu.entity.Menu;

public interface MenuService {

    /**
     * 根据父类code查询菜单列表.<br/>
     * 
     * @author zhanghanlin
     * @param code
     * @return
     * @since JDK 1.7
     */
    public List<Menu> findByParentId(Long parentId);

    /**
     * 获取菜单最大深度.<br/>
     * 
     * @author zhanghanlin
     * @return
     * @since JDK 1.7
     */
    public int maxLevel();

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
     * 
     * 根据Code查询面包屑.<br/>
     * 
     * @author zhanghanlin
     * @param code
     * @return
     * @since JDK 1.7
     */
    public List<String> findMenuNameByCode(String code);

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
    public List<Menu> list(int status);

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
}