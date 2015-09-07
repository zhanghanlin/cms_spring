package com.demo.java.web.shiro.filter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

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
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {

        // TODO Auto-generated method stub
        super.issueSuccessRedirect(request, response);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        if (request.getAttribute(getFailureKeyAttribute()) != null) {
            return true;
        }
        return super.onAccessDenied(request, response, mappedValue);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        User user = userService.findByLogin(subject.getPrincipal().toString());
        if (user != null) {
            subject.getSession().setAttribute(Constants.CURRENT_USER_KEY, user);
            loginLogService.save(IpUtils.getRequestHeaderIpAddr((HttpServletRequest) request));
        }
        WebUtils.issueRedirect(request, response, getSuccessUrl(), null, true);
        return false;
    }
}
