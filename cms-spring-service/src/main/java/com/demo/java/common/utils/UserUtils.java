package com.demo.java.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.demo.java.user.entity.User;
import com.demo.java.utils.Constants;

public class UserUtils {

    public static String getUserName() {
        return getUser().getUserName();
    }

    public static String getLoginName() {
        return SecurityUtils.getSubject().getPrincipal().toString();
    }

    public static Long getUserId() {
        return getUser().getId();
    }

    public static User getUser() {
        Subject subject = SecurityUtils.getSubject();
        return (User) subject.getSession().getAttribute(Constants.CURRENT_USER_KEY);
    }
}
