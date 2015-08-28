package com.demo.java.user.service;

import java.util.List;

import com.demo.java.role.entity.Role;
import com.demo.java.user.entity.UserRole;

public interface UserRoleService {

    /**
     * 分配角色到用户.<br/>
     * 
     * @author zhanghanlin
     * @param id
     * @param roleList
     * @return
     * @since JDK 1.7
     */
    int updateRole2User(Long id, List<Role> roleList);

    /**
     * 根据用户Id查询用户角色.<br/>
     * 
     * @author zhanghanlin
     * @param userId
     * @return
     * @since JDK 1.7
     */
    List<UserRole> findByUserId(Long userId);
}
