package com.demo.java.log.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.java.common.utils.UserUtils;
import com.demo.java.log.dao.LoginLogDao;
import com.demo.java.log.entity.LoginLog;
import com.demo.java.log.service.LoginLogService;

@Service("loginLogService")
public class LoginLogServiceImpl implements LoginLogService {

    @Resource
    LoginLogDao loginLogDao;

    @Override
    public List<LoginLog> findListByPage(int curPage, int pageSize) {
        return loginLogDao.findListByPage((curPage - 1) * pageSize, pageSize);
    }

    @Override
    public int getToalCount() {
        return loginLogDao.getToalCount();
    }

    @Override
    public int save(LoginLog log) {
        log.setCreatedAt(new Date());
        log.setCreatedBy(UserUtils.getUserName());
        log.setLoginAccount(UserUtils.getLoginName());
        log.setLoginName(UserUtils.getUserName());
        return loginLogDao.insert(log);
    }

    @Override
    public int save(String ip) {
        LoginLog log = new LoginLog();
        log.setIp(ip);
        return save(log);
    }
}
