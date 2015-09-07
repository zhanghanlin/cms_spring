package com.demo.java.user.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.java.common.utils.UserUtils;
import com.demo.java.user.dao.UserDao;
import com.demo.java.user.entity.User;
import com.demo.java.user.entity.UserVo;
import com.demo.java.user.service.UserService;
import com.demo.java.utils.string.PatternUtils;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public int add(User t) {
        if (t == null) {
            return 0;
        }
        t.setCreatedAt(new Date());
        t.setCreatedBy(UserUtils.getUserName());
        return userDao.insert(t);
    }

    @Override
    public int getToalCount() {
        return userDao.getToalCount();
    }

    @Override
    public List<UserVo> findListByPage(int pageNo, int pageSize) {
        return userDao.findListByPage((pageNo - 1) * pageSize, pageSize);
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
    public int updateStatus(Long id, int status) {
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        user.setChangedAt(new Date());
        user.setChangedBy(UserUtils.getUserName());
        return userDao.update(user);
    }
}