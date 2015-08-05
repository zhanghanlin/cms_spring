package com.demo.java.role.service;

import java.util.List;

import com.demo.java.role.entity.Role;
import com.demo.java.user.entity.User;

public interface RoleService {

    List<Role> pageList(int curPage, int pageSize);

    int size();

    int add(Role role, User u);

    Role get(Long id);

    int update(Role role, User u);
}
