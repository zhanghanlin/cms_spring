package com.demo.java.user.service;

import java.util.List;
import java.util.Set;

import com.demo.java.user.entity.User;

public interface UserService {

    public boolean save(User t);

    public int size();

    public List<User> pageList(int pageNo, int pageSize);

    public User get(Long id);

    public User findByLogin(String login);

    public Set<String> getRoles(Long id);
}
