package com.demo.java.user.service;

import java.util.List;

import com.demo.java.user.entity.User;

public interface UserService {

    /**
     * 新增用户.<br/>
     * 
     * @author zhanghanlin
     * @param t
     * @return
     * @since JDK 1.7
     */
    public int add(User t);

    /**
     * 
     * 用户总数.<br/>
     * 
     * @author zhanghanlin
     * @return
     * @since JDK 1.7
     */
    public int getToalCount();

    /**
     * 根据用户Id查询用户更.<br/>
     * 
     * @author zhanghanlin
     * @param id
     * @return
     * @since JDK 1.7
     */
    public User get(Long id);

    /**
     * 分页查询.<br/>
     * 
     * @author zhanghanlin
     * @param pageNo
     * @param pageSize
     * @return
     * @since JDK 1.7
     */
    public List<User> findListByPage(int pageNo, int pageSize);

    /**
     * 根据登陆名查询.<br/>
     * 
     * @author zhanghanlin
     * @param login
     * @return
     * @since JDK 1.7
     */
    public User findByLogin(String login);
}
