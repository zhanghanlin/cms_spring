package com.demo.java.log.service;

import java.util.List;

import com.demo.java.log.entity.LoginLog;

public interface LoginLogService {

    /**
     * 根据Log对象保存登陆日志.<br/>
     * 
     * @author zhanghanlin
     * @param log
     * @return
     * @since JDK 1.7
     */
    int save(LoginLog log);

    /**
     * 根据Ip保存.<br/>
     * 
     * @author zhanghanlin
     * @param ip
     * @return
     * @since JDK 1.7
     */
    int save(String ip);

    /**
     * 分页查询.<br/>
     * 
     * @author zhanghanlin
     * @param curPage
     * @param pageSize
     * @return
     * @since JDK 1.7
     */
    List<LoginLog> findListByPage(int curPage, int pageSize);

    /**
     * 日志总数.<br/>
     * 
     * @author zhanghanlin
     * @return
     * @since JDK 1.7
     */
    int getToalCount();
}
