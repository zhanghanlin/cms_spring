package com.demo.java.role.service;

import java.util.List;

import com.demo.java.role.entity.Role;

public interface RoleService {

    /**
     * 分页集合.<br/>
     * 
     * @author zhanghanlin
     * @param curPage
     *            页码
     * @param pageSize
     *            每页数据量
     * @return
     * @since JDK 1.7
     */
    List<Role> pageList(int curPage, int pageSize);

    /**
     * 分页集合总数.<br/>
     * 
     * @author zhanghanlin
     * @return
     * @since JDK 1.7
     */
    int findPageListTotalCount();

    /**
     * 新增角色.<br/>
     * 
     * @author zhanghanlin
     * @param role
     * @return
     * @since JDK 1.7
     */
    int add(Role role);

    /**
     * 获取角色信息.<br/>
     * 
     * @author zhanghanlin
     * @param id
     * @return
     * @since JDK 1.7
     */
    Role get(Long id);

    /**
     * 更新角色.<br/>
     * 
     * @author zhanghanlin
     * @param role
     * @return
     * @since JDK 1.7
     */
    int update(Role role);

    /**
     * 根据Id数组查询.<br/>
     * 
     * @author zhanghanlin
     * @param roleIds
     * @return
     * @since JDK 1.7
     */
    List<Role> findByIds(Long[] roleIds);

    /**
     * 根据状态查询角色列表.<br/>
     * 
     * @author zhanghanlin
     * @param status
     * @return
     * @since JDK 1.7
     */
    List<Role> list(int status);
}
