package com.demo.java.user.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.java.common.dict.Status;
import com.demo.java.user.dao.UserDao;
import com.demo.java.user.entity.User;
import com.demo.java.user.service.UserService;
import com.demo.java.utils.string.PatternUtils;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public boolean save(User t) {
        if (t == null) {
            return false;
        }
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

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public User findByLogin(String login) {
        User t = null;
        if (PatternUtils.regexEmail(login)) {
            t = userDao.getByEmail(login);
        } else if (PatternUtils.regexPhone(login)) {
            t = userDao.getByPhone(login);
        } else {
            t = userDao.getByUserName(login);
        }
        return t;
    }

    @Override
    public Set<String> getRoles(Long id) {
        return null;
    }
}