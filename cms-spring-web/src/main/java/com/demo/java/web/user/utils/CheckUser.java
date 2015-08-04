package com.demo.java.web.user.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.java.user.entity.User;
import com.demo.java.web.common.cookie.LoginCookieUtils;

public class CheckUser {

    public static boolean validLogin(HttpServletRequest request, HttpServletResponse response) {
        if (LoginCookieUtils.validLoginCookie(request, response)) {
            User user = (User) request.getSession().getAttribute("user");
            if (user != null) {
                return true;
            }
        }
        return false;
    }
}
