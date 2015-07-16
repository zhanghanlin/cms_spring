package com.demo.java.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.java.user.dao.UserDao;
import com.demo.java.user.entity.User;
import com.demo.java.user.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public User valid(String userName, String password) {
        User t = userDao.getByUserName(userName);
        if (t != null) {
            if (t.getPassword().equals(password)) {
                return t;
            }
        }
        return null;
    }

}
