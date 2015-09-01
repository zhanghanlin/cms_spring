package com.demo.java.web.shiro.filter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import com.demo.java.user.service.UserService;
import com.demo.java.utils.Constants;

public class SysUserFilter extends PathMatchingFilter {

    @Resource
    UserService userService;

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(Constants.CURRENT_USER_KEY, userService.findByLogin(username));
        return true;
    }
}
