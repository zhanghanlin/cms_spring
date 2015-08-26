package com.demo.java.user.service;

import java.util.List;

import com.demo.java.role.entity.Role;

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
}
