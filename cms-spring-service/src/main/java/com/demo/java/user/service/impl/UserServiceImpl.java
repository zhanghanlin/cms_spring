package com.demo.java.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.java.user.dao.UserDao;
import com.demo.java.user.entity.User;
import com.demo.java.user.service.UserService;
import com.demo.java.utils.crypto.DigestUtils;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public User valid(String userName, String password) {
        User t = userDao.getByUserName(userName);
        if (t != null) {
            String md5Pwd = DigestUtils.md5Hex(password);
            if (t.getPassword().equals(md5Pwd)) {
                return t;
            }
        }
        return null;
    }
}
