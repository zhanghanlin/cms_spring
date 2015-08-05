package com.demo.java.role.service;

import java.util.List;

import com.demo.java.role.entity.Role;

public interface RoleService {

    List<Role> pageList(int curPage, int pageSize);

    int size();
}
