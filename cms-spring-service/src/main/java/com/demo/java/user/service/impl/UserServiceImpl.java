package com.demo.java.user.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.java.common.dict.Status;
import com.demo.java.user.dao.UserDao;
import com.demo.java.user.entity.User;
import com.demo.java.user.service.UserService;
import com.demo.java.utils.crypto.DigestUtils;
import com.demo.java.utils.string.PatternUtils;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public User valid(String userName, String password) {
        User t = null;
        if (PatternUtils.regexEmail(userName)) {
            t = userDao.getByEmail(userName);
        } else if (PatternUtils.regexPhone(userName)) {
            t = userDao.getByPhone(userName);
        } else {
            t = userDao.getByUserName(userName);
        }
        if (t != null) {
            String md5Pwd = DigestUtils.md5Hex(password);
            if (t.getPassword().equals(md5Pwd)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public boolean save(User t) {
        if (t == null) {
            return false;
        }
        String md5Pwd = DigestUtils.md5Hex(t.getPassword());
        t.setPassword(md5Pwd);
        t.setStatus(Status.NORMAL);
        t.setCreatedAt(new Date());
        t.setCreatedBy("System");
        t.setVersion(1);
        int res = userDao.save(t);
        return res > 0;
    }

    @Override
    public int size() {
        return userDao.size();
    }

    @Override
    public List<User> pageList(int pageNo, int pageSize) {
        return userDao.pageList((pageNo - 1) * pageSize, pageSize);
    }
}