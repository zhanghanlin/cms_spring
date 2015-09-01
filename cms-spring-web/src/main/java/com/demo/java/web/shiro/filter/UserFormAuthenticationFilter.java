package com.demo.java.web.shiro.filter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.demo.java.log.service.LoginLogService;
import com.demo.java.user.entity.User;
import com.demo.java.user.service.UserService;
import com.demo.java.utils.Constants;
import com.demo.java.web.common.util.IpUtils;

public class UserFormAuthenticationFilter extends FormAuthenticationFilter {

    @Resource
    UserService userService;

    @Resource
    LoginLogService loginLogService;

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        User user = userService.findByLogin(subject.getPrincipal().toString());
        if (user != null) {
            subject.getSession().setAttribute(Constants.CURRENT_USER_KEY, user);
            loginLogService.save(IpUtils.getRequestHeaderIpAddr((HttpServletRequest) request));
        }
        return super.onLoginSuccess(token, subject, request, response);
    }
}
