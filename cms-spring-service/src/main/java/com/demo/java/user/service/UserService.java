package com.demo.java.user.service;

import java.util.List;

import com.demo.java.user.entity.User;

public interface UserService {

    public User valid(String userName, String password);

    public boolean save(User t);

    public int size();

    public List<User> pageList(int pageNo, int pageSize);
}
