package com.demo.java.web.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.java.utils.string.StringUtils;
import com.demo.java.web.cookie.LoginCookieUtils;

public class AuthenticationFiter implements Filter {

    private String exclude;

    private String[] excludes;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        exclude = filterConfig.getInitParameter("exclude");
        if (StringUtils.isNotBlank(exclude)) {
            excludes = exclude.split(",");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String servletPath = request.getServletPath();
        for (String path : excludes) {
            if (Pattern.matches(path + "(.*)", servletPath)) {
                chain.doFilter(request, response);
                return;
            }
            if (request.getContextPath().equals(path)) {
                chain.doFilter(request, response);
                return;
            }
        }
        if (!LoginCookieUtils.validLoginCookie(request, response)) {
            request.getRequestDispatcher("/login").forward(request, response);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}