package com.demo.java.web.shiro.filter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import com.demo.java.user.entity.User;
import com.demo.java.user.service.UserService;
import com.demo.java.utils.Constants;

public class UserSetting extends AccessControlFilter {

    @Resource
    UserService userService;

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject == null) {
            return false;
        }
        HttpSession session = ((HttpServletRequest) request).getSession();
        User current_user = (User) session.getAttribute(Constants.current_user_key);
        if (current_user == null) {
            current_user = userService.findByLogin(subject.getPrincipal().toString());
        }
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        // TODO Auto-generated method stub
        return false;
    }

}
