package com.demo.java.web.shiro.filter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;

import com.demo.java.user.entity.User;
import com.demo.java.user.service.UserService;
import com.demo.java.utils.Constants;

public class SysUserFilter extends PathMatchingFilter {

    @Resource
    UserService userService;

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null) {
            return false;
        }
        Object sessionUser = subject.getSession().getAttribute(Constants.CURRENT_USER_KEY);
        if (sessionUser == null) {
            String username = subject.getPrincipal().toString();
            User user = userService.findByLogin(username);
            subject.getSession().setAttribute(Constants.CURRENT_USER_KEY, user);
        }
        return true;
    }
}
